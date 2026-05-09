package com.trabalhopratico.sistemabiblioteca.dtos;

import java.time.LocalDateTime;

public record ErroResponse(int status, String mensagem, LocalDateTime dataHora, Object erro) {}
