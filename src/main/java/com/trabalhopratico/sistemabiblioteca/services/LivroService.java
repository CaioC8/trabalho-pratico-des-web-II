package com.trabalhopratico.sistemabiblioteca.services;

import com.trabalhopratico.sistemabiblioteca.dtos.AtualizarLivroDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CriarLivroDto;
import com.trabalhopratico.sistemabiblioteca.dtos.LivroCompletoDto;
import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import com.trabalhopratico.sistemabiblioteca.entities.Livro;
import com.trabalhopratico.sistemabiblioteca.repositories.LivroRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LivroService {
  private final LivroRepository livroRepository;
  private final CategoriaService categoriaService;

  public LivroService(LivroRepository livroRepository, CategoriaService categoriaService) {
    this.livroRepository = livroRepository;
    this.categoriaService = categoriaService;
  }

  public LivroCompletoDto create(CriarLivroDto livroDto) {
    Categoria categoria = this.categoriaService.findById(livroDto.categoriaId());

    Livro livro = new Livro();
    livro.setTitulo(livroDto.titulo());
    livro.setIsbn(livroDto.isbn());
    livro.setCategoria(categoria);

    Livro livroCriado = this.livroRepository.save(livro);

    return LivroCompletoDto.toLivroCompletoDto(livroCriado);
  }

  public List<LivroCompletoDto> getAll() {
    return this.livroRepository.findAll().stream()
        .map((livro) -> LivroCompletoDto.toLivroCompletoDto(livro))
        .toList();
  }

  public LivroCompletoDto getById(Long id) {
    Livro livro =
        this.livroRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

    return LivroCompletoDto.toLivroCompletoDto(livro);
  }

  public LivroCompletoDto update(Long id, AtualizarLivroDto livroDto) {
    Livro livro =
        this.livroRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

    livroDto.titulo().ifPresent(livro::setTitulo);

    livroDto.isbn().ifPresent(livro::setIsbn);

    livroDto
        .categoriaId()
        .ifPresent(
            categoriaId -> {
              Categoria novaCategoria = this.categoriaService.findById(categoriaId);
              livro.setCategoria(novaCategoria);
            });

    Livro livroAtualizado = this.livroRepository.save(livro);

    return LivroCompletoDto.toLivroCompletoDto(livroAtualizado);
  }

  public void delete(Long id) {
    Livro livro =
        this.livroRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

    this.livroRepository.delete(livro);
  }
}
