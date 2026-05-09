package com.trabalhopratico.sistemabiblioteca.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Optional;

public record AtualizarLivroDto(
    Optional<String> titulo,
    Optional<@Size(min = 10, message = "O ISBN deve ter no mínimo 10 caractéres.") String> isbn,
    Optional<@Positive(message = "Categoria inválida.") Long> categoriaId) {}
