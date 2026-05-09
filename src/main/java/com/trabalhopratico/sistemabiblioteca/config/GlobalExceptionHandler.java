package com.trabalhopratico.sistemabiblioteca.config;

import com.trabalhopratico.sistemabiblioteca.dtos.CampoErro;
import com.trabalhopratico.sistemabiblioteca.dtos.ErroResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErroResponse> lidarComErros(ResponseStatusException exception) {
    ErroResponse erro =
        new ErroResponse(
            exception.getStatusCode().value(),
            "Ocorreu um erro.",
            LocalDateTime.now(),
            exception.getReason());
    return ResponseEntity.status(exception.getStatusCode()).body(erro);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> lidarComErrosDeValidacao(
      MethodArgumentNotValidException exception) {
    List<CampoErro> listaDeErros =
        exception.getBindingResult().getFieldErrors().stream()
            .map(erro -> new CampoErro(erro.getField(), erro.getDefaultMessage()))
            .toList();

    ErroResponse erro =
        new ErroResponse(400, "Falha na validação dos dados.", LocalDateTime.now(), listaDeErros);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroResponse> lidarComErroInterno(Exception exception) {
    ErroResponse erro =
        new ErroResponse(
            500,
            "Ocorreu um erro inesperado. Tente novamente mais tarde.",
            LocalDateTime.now(),
            exception.getMessage());

    exception.printStackTrace();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
  }
}
