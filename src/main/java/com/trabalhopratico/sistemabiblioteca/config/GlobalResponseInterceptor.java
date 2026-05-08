package com.trabalhopratico.sistemabiblioteca.config;

import com.trabalhopratico.sistemabiblioteca.annotations.MensagemResponse;
import com.trabalhopratico.sistemabiblioteca.dtos.ErroResponse;
import com.trabalhopratico.sistemabiblioteca.dtos.SucessoResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.trabalhopratico.sistemabiblioteca.controllers")
public class GlobalResponseInterceptor implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return !returnType.getParameterType().equals(ErroResponse.class)
        && !returnType.getParameterType().equals(SucessoResponse.class);
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (body == null) {
      return null;
    }

    int status = 200;
    if (response instanceof ServletServerHttpResponse servletResponse) {
      status = servletResponse.getServletResponse().getStatus();
    }

    String mensagem = "Operacão realizada com sucesso.";

    MensagemResponse mensagemResponse = returnType.getMethodAnnotation(MensagemResponse.class);

    if (mensagemResponse != null) {
      mensagem = mensagemResponse.value();
    }

    return new SucessoResponse<>(status, mensagem, body);
  }
}
