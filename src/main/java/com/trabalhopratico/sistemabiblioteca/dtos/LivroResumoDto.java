package com.trabalhopratico.sistemabiblioteca.dtos;

import com.trabalhopratico.sistemabiblioteca.entities.Livro;

public record LivroResumoDto(Long id, String titulo, String isbn) {
  public static LivroResumoDto toLivroResumoDto(Livro livro) {
    return new LivroResumoDto(livro.getId(), livro.getTitulo(), livro.getIsbn());
  }
}
