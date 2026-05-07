package com.trabalhopratico.sistemabiblioteca.controllers;

import com.trabalhopratico.sistemabiblioteca.dtos.AtualizarLivroDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CriarLivroDto;
import com.trabalhopratico.sistemabiblioteca.dtos.LivroCompletoDto;
import com.trabalhopratico.sistemabiblioteca.services.LivroService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {
  private final LivroService livroService;

  public LivroController(LivroService livroService) {
    this.livroService = livroService;
  }

  @PostMapping
  public ResponseEntity<LivroCompletoDto> create(@RequestBody CriarLivroDto livroDto) {
    LivroCompletoDto livroCriado = this.livroService.create(livroDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
  }

  @GetMapping
  public ResponseEntity<List<LivroCompletoDto>> getAll() {
    List<LivroCompletoDto> livros = this.livroService.getAll();

    return ResponseEntity.status(HttpStatus.OK).body(livros);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LivroCompletoDto> getById(@PathVariable Long id) {
    LivroCompletoDto livro = this.livroService.getById(id);

    return ResponseEntity.status(HttpStatus.OK).body(livro);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LivroCompletoDto> update(
      @PathVariable Long id, @RequestBody AtualizarLivroDto livroDto) {
    LivroCompletoDto livro = this.livroService.update(id, livroDto);

    return ResponseEntity.status(HttpStatus.OK).body(livro);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.livroService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
