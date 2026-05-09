package com.trabalhopratico.sistemabiblioteca.dtos;

import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import java.util.List;

public record CategoriaCompletoDto(Long id, String nome, List<LivroResumoDto> livros) {
  public static CategoriaCompletoDto toCategoriaCompletoDto(Categoria categoria) {
    List<LivroResumoDto> livros =
        categoria.getLivros().stream()
            .map((livro) -> new LivroResumoDto(livro.getId(), livro.getTitulo(), livro.getIsbn()))
            .toList();

    return new CategoriaCompletoDto(categoria.getId(), categoria.getNome(), livros);
  }
}
