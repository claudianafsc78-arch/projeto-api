# DevShowcase API — Etapa 1

Backend da plataforma DevShowcase: modelagem de domínio, persistência relacional (H2)
e endpoints REST básicos, construído com **Java 17 + Spring Boot 3**.

## Stack

- Spring Boot 3.3 (Web, Data JPA, Validation)
- Banco H2 (arquivo local, sem necessidade de instalar nada)
- Lombok
- Maven

## Entidades e relacionamentos

- `Profile` **1:N** `Project`
- `Project` **N:N** `Technology`
- `Project` **1:N** `Feedback`

## Como rodar

Pré-requisitos: Java 17+ e Maven (ou use o `mvnw` se adicionar o wrapper).

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.
Console do H2 (opcional, para ver as tabelas): `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:file:./data/devshowcase`, usuário `sa`, senha em branco).

## Endpoints implementados

| Método | Rota                     | Descrição                          |
|--------|--------------------------|-------------------------------------|
| POST   | `/api/profiles`          | Cria um perfil de desenvolvedor     |
| GET    | `/api/profiles/{id}`     | Busca um perfil por id              |
| POST   | `/api/technologies`      | Cadastra uma tecnologia             |
| GET    | `/api/technologies`      | Lista todas as tecnologias          |
| POST   | `/api/projects`          | Cadastra um projeto                 |
| GET    | `/api/projects`          | Lista todos os projetos             |

### Exemplos de payload

**POST /api/profiles**
```json
{
  "fullName": "Maria Silva",
  "email": "maria@email.com",
  "bio": "Desenvolvedora backend apaixonada por Java",
  "githubUrl": "https://github.com/mariasilva"
}
```

**POST /api/technologies**
```json
{
  "name": "Spring Boot"
}
```

**POST /api/projects** (crie antes um profile e ao menos uma technology, e use os ids retornados)
```json
{
  "title": "DevShowcase API",
  "description": "API para exibir portfolio de projetos",
  "repositoryUrl": "https://github.com/mariasilva/devshowcase-api",
  "profileId": 1,
  "technologyIds": [1, 2]
}
```

## Validações

- `fullName`, `email` (formato válido) e obrigatórios em `Profile`.
- `name` obrigatório e único em `Technology`.
- `title`, `repositoryUrl` (URL válida), `profileId` e `technologyIds` obrigatórios em `Project`.
- Erros de validação retornam `400` com detalhamento por campo.
- Recursos não encontrados (perfil/tecnologia inexistente) retornam `404`.

## Testando com Postman

Importe o arquivo `postman_collection.json` (na raiz do projeto) no Postman.
As requisições já estão na ordem certa: criar perfil → criar tecnologia → criar projeto → listar.

## Deploy no Render

O projeto inclui um `Dockerfile` pronto. No Render, crie um **Web Service**, conecte
este repositório, escolha **Docker** como ambiente (o Render detecta o Dockerfile
automaticamente) e faça o deploy. A aplicação escuta na porta definida pela variável
de ambiente `PORT`, que o Render injeta automaticamente.

⚠️ **Atenção**: o H2 usado aqui é um arquivo local dentro do container. No plano
gratuito do Render, o disco é efêmero — isso significa que, a cada novo deploy ou
reinício do serviço, o banco volta zerado. Isso é aceitável para fins de demonstração
da API, mas não é uma persistência real "em produção". Se precisar de persistência
de verdade entre reinícios, use o PostgreSQL gratuito do próprio Render e ajuste o
`application.properties` para apontar para ele.

