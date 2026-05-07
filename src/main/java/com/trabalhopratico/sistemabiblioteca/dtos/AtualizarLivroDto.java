package com.trabalhopratico.sistemabiblioteca.dtos;

import java.util.Optional;

public record AtualizarLivroDto(
    Optional<String> titulo, Optional<String> isbn, Optional<Long> categoriaId) {}
