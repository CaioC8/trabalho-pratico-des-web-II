package com.trabalhopratico.sistemabiblioteca.repositories;

import com.trabalhopratico.sistemabiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}
