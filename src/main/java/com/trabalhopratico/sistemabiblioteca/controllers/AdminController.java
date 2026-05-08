package com.trabalhopratico.sistemabiblioteca.controllers;

import com.trabalhopratico.sistemabiblioteca.services.SeederService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
  private final SeederService seederService;
  private final JdbcTemplate jdbcTemplate;

  public AdminController(SeederService seederService, JdbcTemplate jdbcTemplate) {

    this.seederService = seederService;
    this.jdbcTemplate = jdbcTemplate;
  }

  @DeleteMapping("/reset")
  public ResponseEntity<Void> deleteAll() {
    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

    jdbcTemplate.execute("TRUNCATE TABLE tb_livro RESTART IDENTITY");
    jdbcTemplate.execute("TRUNCATE TABLE tb_categoria RESTART IDENTITY");

    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

    System.out.println("Banco resetado com sucesso!");

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/recreate")
  public ResponseEntity<Void> recreate() {
    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

    jdbcTemplate.execute("TRUNCATE TABLE tb_livro RESTART IDENTITY");
    jdbcTemplate.execute("TRUNCATE TABLE tb_categoria RESTART IDENTITY");

    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

    seederService.seed();

    System.out.println("Banco recriado com sucesso!");

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("/seed")
  public ResponseEntity<Void> seed() {
    this.seederService.seed();

    System.out.println("Seed executado com sucesso!");

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
