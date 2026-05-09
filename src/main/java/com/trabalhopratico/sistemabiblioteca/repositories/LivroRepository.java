package com.trabalhopratico.sistemabiblioteca.repositories;

import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import com.trabalhopratico.sistemabiblioteca.entities.Livro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
  Optional<Livro> findByTitulo(String titulo);

  List<Livro> findByTituloContainingIgnoreCase(String titulo);

  List<Livro> findByCategoria(Categoria categoria);
}
