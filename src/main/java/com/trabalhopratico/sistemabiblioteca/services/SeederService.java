package com.trabalhopratico.sistemabiblioteca.services;

import com.trabalhopratico.sistemabiblioteca.entities.Categoria;
import com.trabalhopratico.sistemabiblioteca.entities.Livro;
import com.trabalhopratico.sistemabiblioteca.repositories.CategoriaRepository;
import com.trabalhopratico.sistemabiblioteca.repositories.LivroRepository;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class SeederService {
  private final CategoriaRepository categoriaRepository;
  private final LivroRepository livroRepository;

  public SeederService(CategoriaRepository categoriaRepository, LivroRepository livroRepository) {
    this.categoriaRepository = categoriaRepository;
    this.livroRepository = livroRepository;
  }

  public void seed() {
    if (categoriaRepository.count() == 0) {

      Categoria ficcao = new Categoria();
      ficcao.setNome("Ficção Científica");
      Categoria fantasia = new Categoria();
      fantasia.setNome("Fantasia");
      Categoria terror = new Categoria();
      terror.setNome("Terror");
      Categoria romance = new Categoria();
      romance.setNome("Romance");
      Categoria misterio = new Categoria();
      misterio.setNome("Mistério");
      Categoria autoajuda = new Categoria();
      autoajuda.setNome("Autoajuda");
      Categoria tecnologia = new Categoria();
      tecnologia.setNome("Tecnologia");

      categoriaRepository.saveAll(
          Arrays.asList(ficcao, fantasia, terror, romance, misterio, autoajuda, tecnologia));

      livroRepository.saveAll(
          Arrays.asList(
              // Ficção Científica
              criarLivro("Duna", "9780441172719", ficcao),
              criarLivro("Andróides Sonham com Ovelhas Elétricas?", "9788576573128", ficcao),

              // Fantasia
              criarLivro("O Hobbit", "9788595084741", fantasia),
              criarLivro("A Guerra dos Tronos", "9788544102923", fantasia),

              // Terror
              criarLivro("O Iluminado", "9788532530639", terror),
              criarLivro("It: A Coisa", "9788535924558", terror),

              // Romance
              criarLivro("Orgulho e Preconceito", "9788563536037", romance),
              criarLivro("Como Eu Era Antes de Você", "9788580573169", romance),

              // Mistério
              criarLivro("O Assassinato no Expresso do Oriente", "9788525057044", misterio),
              criarLivro("Sherlock Holmes: Um Estudo em Vermelho", "9788537803363", misterio),

              // Autoajuda
              criarLivro("O Poder do Hábito", "9788539004119", autoajuda),
              criarLivro("Pai Rico, Pai Pobre", "9788535287578", autoajuda),

              // Tecnologia
              criarLivro("Código Limpo", "9788576082675", tecnologia),
              criarLivro("Arquitetura Limpa", "9788550804606", tecnologia)));

      System.out.println("Seed finalizado: 7 categorias e 14 livros inseridos!");
    } else {
      System.out.println("O banco já tem dados inseridos. Seed ignorado.");
    }
  }

  private Livro criarLivro(String titulo, String isbn, Categoria categoria) {
    Livro livro = new Livro();
    livro.setTitulo(titulo);
    livro.setIsbn(isbn);
    livro.setCategoria(categoria);
    return livro;
  }
}
