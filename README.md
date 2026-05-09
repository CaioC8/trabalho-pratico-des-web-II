# Sistema de Biblioteca - Documentação da API

## Sumário

- [Entregas](#entregas)
- [Banco de dados](#banco-de-dados)
- [Padrão de Respostas](#padrão-de-respostas)
  - [Resposta de Sucesso](#resposta-de-sucesso)
  - [Resposta de Erro](#resposta-de-erro)
- [Endpoints (Rotas)](#endpoints-rotas)
  - [📚 Livros (`/livros`)](#livros)
  - [🏷️ Categorias (`/categorias`)](#categorias)
  - [⚙️ Administração e Banco de Dados (`/admin`)](#admin)

---

## Entregas

O repositório está divido nas branches:

- **main**.
- **entrega-1**: projeto com os arquivos necessários para a primeira entrega, contendo as entidades e os repositórios.
- **entrega-2**: projeto completo com os arquivos necessários para a segunda entrega, contendo os controllers e services.

---

## Banco de dados

O banco de dados é foi criado na raiz do projeto em `./data/testdb.mv.db`. Segue a URL para se conectar ao banco no console do h2:

```bash
jdbc:h2:file:./data/testdb
```

Ao executar o projeto, um seed será executado, estando pronto para uso.

---

## Padrão de Respostas

### Resposta de Sucesso

Todas as respostas de sucesso que retornam um corpo são interceptadas pelo `GlobalResponseInterceptor` e padronizadas no seguinte formato:

```json
{
  "status": 200,
  "mensagem": "Operacão realizada com sucesso.",
  "dados": {
    // Dados retornados pela rota (ex: lista de livros, objeto criado, etc.)
  }
}
```

> **Nota:** Em casos de deleção ou rotas administrativas que retornam `204 No Content`, a API não retorna um corpo.

### Resposta de Erro

Qualquer erro ocorrido no processamento da requisição é tratado globalmente pelo `GlobalExceptionHandler` e retornado no seguinte formato:

```json
{
  "status": 400,
  "mensagem": "Falha na validação dos dados.",
  "dataHora": "2026-05-08T15:16:45",
  "erro": [
    // Também pode ser a mensagem definida nas excessões nos serviços
    {
      "campo": "titulo",
      "mensagem": "não pode estar em branco"
    }
  ]
}
```

---

## Endpoints (Rotas)

<a id="livros"></a>

### 📚 Livros (`/livros`)

- **`POST /livros`**
  - **Descrição:** Cria um novo livro.
  - **Corpo da Requisição:**

  ```json
  {
    "titulo": "titulo",
    "isbn": "isbn com pelo menos 10 caracteres",
    "categoriaId": 1
  }
  ```

  - **Resposta de Sucesso:**

  ```json
  {
    "status": 201,
    "mensagem": "Livro criado com sucesso.",
    "dados": {
      "id": 15,
      "titulo": "Senhor dos Anéis",
      "isbn": "9185144102926",
      "categoria": {
        "id": 2,
        "nome": "Fantasia"
      }
    }
  }
  ```

- **`GET /livros`**
  - **Descrição:** Lista todos os livros cadastrados.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": [
      {
        "id": 15,
        "titulo": "Senhor dos Anéis",
        "isbn": "9185144102926",
        "categoria": {
          "id": 2,
          "nome": "Fantasia"
        }
      }
    ]
  }
  ```

- **`GET /livros/{id}`**
  - **Descrição:** Busca um livro pelo seu ID.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": {
      "id": 15,
      "titulo": "Senhor dos Anéis",
      "isbn": "9185144102926",
      "categoria": {
        "id": 2,
        "nome": "Fantasia"
      }
    }
  }
  ```

- **`GET /livros/titulo/{titulo}`**
  - **Descrição:** Busca um livro pelo seu título **exato**.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": {
      "id": 15,
      "titulo": "Senhor dos Anéis",
      "isbn": "9185144102926",
      "categoria": {
        "id": 2,
        "nome": "Fantasia"
      }
    }
  }
  ```

- **`GET /livros/busca/{titulo}`**
  - **Descrição:** Busca livros que contenham a **parte do título** informado.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": [
      {
        "id": 15,
        "titulo": "Senhor dos Anéis",
        "isbn": "9185144102926",
        "categoria": {
          "id": 2,
          "nome": "Fantasia"
        }
      }
    ]
  }
  ```

- **`GET /livros/categoria/{categoriaId}`**
  - **Descrição:** Busca todos os livros associados a uma determinada categoria pelo seu ID.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": [
      {
        "id": 15,
        "titulo": "Senhor dos Anéis",
        "isbn": "9185144102926",
        "categoria": {
          "id": 2,
          "nome": "Fantasia"
        }
      }
    ]
  }
  ```

- **`PATCH /livros/{id}`**
  - **Descrição:** Atualiza os dados de um livro existente parcialmente.
  - **Corpo da Requisição:** _(Apenas os campos que deseja alterar são necessários)_

  ```json
  {
    "titulo": "Novo Título do Livro"
  }
  ```

  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Livro atualizado com sucesso.",
    "dados": {
      "id": 15,
      "titulo": "Novo Título do Livro",
      "isbn": "9185144102926",
      "categoria": {
        "id": 2,
        "nome": "Fantasia"
      }
    }
  }
  ```

- **`DELETE /livros/{id}`**
  - **Descrição:** Remove um livro pelo seu ID.
  - **Resposta de Sucesso:** `204 No Content` - Sem corpo na resposta.

<a id="categorias"></a>

### 🏷️ Categorias (`/categorias`)

- **`POST /categorias`**
  - **Descrição:** Cria uma nova categoria.
  - **Corpo da Requisição:**

  ```json
  {
    "nome": "Ficção Científica"
  }
  ```

  - **Resposta de Sucesso:**

  ```json
  {
    "status": 201,
    "mensagem": "Categoria criada com sucesso.",
    "dados": {
      "id": 3,
      "nome": "Ficção Científica",
      "livros": []
    }
  }
  ```

- **`GET /categorias`**
  - **Descrição:** Lista todas as categorias cadastradas.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": [
      {
        "id": 3,
        "nome": "Ficção Científica",
        "livros": []
      }
    ]
  }
  ```

- **`GET /categorias/{id}`**
  - **Descrição:** Busca uma categoria pelo seu ID.
  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Operacão realizada com sucesso.",
    "dados": {
      "id": 3,
      "nome": "Ficção Científica",
      "livros": [
        {
          "id": 20,
          "titulo": "Duna",
          "isbn": "1234567890123"
        }
      ]
    }
  }
  ```

- **`PATCH /categorias/{id}`**
  - **Descrição:** Atualiza os dados de uma categoria existente parcialmente.
  - **Corpo da Requisição:**

  ```json
  {
    "nome": "Ficção"
  }
  ```

  - **Resposta de Sucesso:**

  ```json
  {
    "status": 200,
    "mensagem": "Categoria atualizada com sucesso.",
    "dados": {
      "id": 3,
      "nome": "Ficção",
      "livros": []
    }
  }
  ```

- **`DELETE /categorias/{id}`**
  - **Descrição:** Remove uma categoria pelo seu ID.
  - **Resposta de Sucesso:** `204 No Content` - Sem corpo na resposta.

<a id="admin"></a>

### ⚙️ Administração e Banco de Dados (`/admin`)

Essas rotas são utilizadas principalmente para facilitar o desenvolvimento e os testes do sistema, manipulando o estado do banco de dados (apagar registros, recriar massa de dados, etc.).

- **GET /h2-console**
  - **Descrição:** Gerenciador do banco de dados.
  - **Resposta de Sucesso:** Página do gerenciador.

- **`DELETE /admin/reset`**
  - **Descrição:** Limpa todos os registros das tabelas de livros e categorias.
  - **Resposta de Sucesso:** `204 No Content`.

- **`DELETE /admin/recreate`**
  - **Descrição:** Limpa os dados e logo após aciona o `seeder` para popular o banco novamente com os dados iniciais.
  - **Resposta de Sucesso:** `204 No Content`.

- **`POST /admin/seed`**
  - **Descrição:** Executa o `seeder` para injetar alguns dados (categorias e livros) de forma automatizada no banco de dados.
  - **Resposta de Sucesso:** `204 No Content`.
