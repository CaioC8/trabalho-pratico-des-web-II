package com.trabalhopratico.sistemabiblioteca.config;

import com.trabalhopratico.sistemabiblioteca.services.SeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

  private final SeederService seederService;

  public DatabaseSeeder(SeederService seederService) {
    this.seederService = seederService;
  }

  @Override
  public void run(String... args) throws Exception {
    this.seederService.seed();
  }
}
