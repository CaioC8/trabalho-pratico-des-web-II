package com.trabalhopratico.sistemabiblioteca.dtos;

import com.trabalhopratico.sistemabiblioteca.entities.Livro;

public record LivroCompletoDto(Long id, String titulo, String isbn, CategoriaResumoDto categoria) {
  public static LivroCompletoDto toLivroCompletoDto(Livro livro) {
    CategoriaResumoDto categoria =
        new CategoriaResumoDto(livro.getCategoria().getId(), livro.getCategoria().getNome());

    return new LivroCompletoDto(livro.getId(), livro.getTitulo(), livro.getIsbn(), categoria);
  }
}
