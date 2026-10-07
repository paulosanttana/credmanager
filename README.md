# Credenciamento

Projeto Spring Boot para gerenciamento de credenciamento de usuários.

## Requisitos

- Java 17+
- Maven 3.9+

## Como rodar

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints principais

### Usuários
- `GET /api/usuarios`
- `GET /api/usuarios/{id}`
- `POST /api/usuarios`
- `PUT /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`

### Credenciais
- `GET /api/credenciais`
- `GET /api/credenciais/{id}`
- `GET /api/credenciais/usuario/{usuarioId}`
- `POST /api/credenciais`
- `PUT /api/credenciais/{id}`
- `DELETE /api/credenciais/{id}`

## Banco de dados

O projeto usa H2 em memória por padrão.
Acesse o console em:

```text
http://localhost:8080/h2-console
```

JDBC URL:
```text
jdbc:h2:mem:credenciamento
```

Usuário: `sa`
Senha: vazio
