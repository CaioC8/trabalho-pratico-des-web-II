package com.trabalhopratico.sistemabiblioteca.controllers;

import com.trabalhopratico.sistemabiblioteca.dtos.AtualizarCategoriaDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CategoriaCompletoDto;
import com.trabalhopratico.sistemabiblioteca.dtos.CriarCategoriaDto;
import com.trabalhopratico.sistemabiblioteca.services.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {
  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @PostMapping
  public ResponseEntity<CategoriaCompletoDto> create(@RequestBody CriarCategoriaDto categoriaDto) {
    CategoriaCompletoDto categoria = this.categoriaService.create(categoriaDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
  }

  @GetMapping
  public ResponseEntity<List<CategoriaCompletoDto>> getAll() {
    List<CategoriaCompletoDto> livros = this.categoriaService.getAll();

    return ResponseEntity.status(HttpStatus.OK).body(livros);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaCompletoDto> getById(@PathVariable Long id) {
    CategoriaCompletoDto livro = this.categoriaService.getById(id);

    return ResponseEntity.status(HttpStatus.OK).body(livro);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CategoriaCompletoDto> update(
      @PathVariable Long id, @RequestBody AtualizarCategoriaDto categoriaDto) {
    CategoriaCompletoDto categoria = this.categoriaService.update(id, categoriaDto);

    return ResponseEntity.status(HttpStatus.OK).body(categoria);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.categoriaService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
