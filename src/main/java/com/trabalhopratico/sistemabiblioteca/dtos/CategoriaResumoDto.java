package com.trabalhopratico.sistemabiblioteca.dtos;

import com.trabalhopratico.sistemabiblioteca.entities.Categoria;

public record CategoriaResumoDto(Long id, String nome) {
  public static CategoriaResumoDto toCategoriaResumoDto(Categoria categoria) {
    return new CategoriaResumoDto(categoria.getId(), categoria.getNome());
  }
}
