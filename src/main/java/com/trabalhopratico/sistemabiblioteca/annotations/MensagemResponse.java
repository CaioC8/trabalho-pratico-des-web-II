package com.trabalhopratico.sistemabiblioteca.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MensagemResponse {
  String value() default "Operação realizada com sucesso.";
}
