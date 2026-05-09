package com.trabalhopratico.sistemabiblioteca.dtos;

public record SucessoResponse<T>(int status, String mensagem, T dados) {}
