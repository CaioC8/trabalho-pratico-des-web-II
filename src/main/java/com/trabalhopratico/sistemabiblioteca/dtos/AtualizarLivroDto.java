package com.trabalhopratico.sistemabiblioteca.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Optional;

public record AtualizarLivroDto(
    Optional<String> titulo,
    @Size(min = 10, message = "O ISBN deve ter no mínimo 10 caractéres.") Optional<String> isbn,
    @Positive(message = "Categoria inválida.") Optional<Long> categoriaId) {}
