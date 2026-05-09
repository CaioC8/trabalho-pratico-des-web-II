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
    List<Livro> livors = this.livroRepository.findAll();

    return livors.stream().map((livro) -> LivroCompletoDto.toLivroCompletoDto(livro)).toList();
  }

  public LivroCompletoDto getById(Long id) {
    Livro livro = this.findById(id);

    return LivroCompletoDto.toLivroCompletoDto(livro);
  }

  public LivroCompletoDto getByTitulo(String titulo) {
    Livro livro =
        this.livroRepository
            .findByTitulo(titulo)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

    return LivroCompletoDto.toLivroCompletoDto(livro);
  }

  public List<LivroCompletoDto> getByTituloParcial(String titulo) {
    List<Livro> livros = this.livroRepository.findByTituloContainingIgnoreCase(titulo);

    return livros.stream().map((livro) -> LivroCompletoDto.toLivroCompletoDto(livro)).toList();
  }

  public List<LivroCompletoDto> getByCategoria(Long categoriaId) {
    Categoria categoria = this.categoriaService.findById(categoriaId);

    List<Livro> livros = this.livroRepository.findByCategoria(categoria);

    return livros.stream().map((livro) -> LivroCompletoDto.toLivroCompletoDto(livro)).toList();
  }

  public LivroCompletoDto update(Long id, AtualizarLivroDto livroDto) {
    Livro livro = this.findById(id);

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
    Livro livro = this.findById(id);

    this.livroRepository.delete(livro);
  }

  public Livro findById(Long id) {
    return this.livroRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));
  }
}
