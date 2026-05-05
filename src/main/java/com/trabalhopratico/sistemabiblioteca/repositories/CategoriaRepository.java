package com.trabalhopratico.sistemabiblioteca.repositories;

import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
