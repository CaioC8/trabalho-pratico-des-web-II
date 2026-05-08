package com.trabalhopratico.sistemabiblioteca.services;

import com.trabalhopratico.sistemabiblioteca.dtos.AtualizarCategoriaDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CategoriaCompletoDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CriarCategoriaDto;
import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import com.trabalhopratico.sistemabiblioteca.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public CategoriaCompletoDto create(CriarCategoriaDto categoriaDto) {
    Categoria categoria = new Categoria();
    categoria.setNome(categoriaDto.nome());

    Categoria categoriaCriada = this.categoriaRepository.save(categoria);

    return CategoriaCompletoDto.toCategoriaCompletoDto(categoriaCriada);
  }

  public List<CategoriaCompletoDto> getAll() {
    return this.categoriaRepository.findAll().stream()
        .map((categoria) -> CategoriaCompletoDto.toCategoriaCompletoDto(categoria))
        .toList();
  }

  public CategoriaCompletoDto getById(Long id) {
    Categoria categoria = this.findById(id);

    return CategoriaCompletoDto.toCategoriaCompletoDto(categoria);
  }

  public CategoriaCompletoDto update(Long id, AtualizarCategoriaDto categoriaDto) {
    Categoria categoria = this.findById(id);

    categoriaDto.nome().ifPresent(categoria::setNome);

    Categoria categoriaAtualizada = this.categoriaRepository.save(categoria);

    return CategoriaCompletoDto.toCategoriaCompletoDto(categoriaAtualizada);
  }

  public void delete(Long id) {
    Categoria categoria = this.findById(id);

    this.categoriaRepository.delete(categoria);
  }

  public Categoria findById(Long id) {
    return this.categoriaRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
  }
}
