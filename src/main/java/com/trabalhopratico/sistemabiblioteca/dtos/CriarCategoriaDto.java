package com.trabalhopratico.sistemabiblioteca.dtos;

import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaDto(@NotBlank(message = "Preencha o campo nome.") String nome) {}
