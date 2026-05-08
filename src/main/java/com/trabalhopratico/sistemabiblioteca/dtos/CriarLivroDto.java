package com.trabalhopratico.sistemabiblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CriarLivroDto(
    @NotBlank(message = "Preencha o campo título.") String titulo,
    @NotBlank(message = "Preencha o campo ISBN.")
        @Size(min = 10, message = "O ISBN deve ter no mínimo 10 caractéres.")
        String isbn,
    @NotNull(message = "Preencha o campo categoria.") @Positive(message = "Categoria inválida.")
        Long categoriaId) {}
