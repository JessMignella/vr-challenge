# VR Autorizador

VR Autorizador é um projeto de exemplo que utiliza Spring Boot para criar um serviço de autorização de transações com cartões. O serviço permite a criação de novos cartões, consulta de saldo e autorização de transações.

## Requisitos

- Java 17
- Docker
- Docker Compose
- MySQL

## Configuração

### Configuração do Banco de Dados

### Arquivo `docker-compose.yml`

O arquivo `docker-compose.yml` está configurado para criar um contêiner MySQL e o serviço Spring Boot.

```yaml
version: '3.8'

services:
  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: example
      MYSQL_DATABASE: miniautorizador
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/miniautorizador
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 


```
## Endpoints
### Swagger
A documentação da API pode ser acessada em: http://localhost:8080/swagger-ui/index.html

### Cartões
- Criar Cartão

        POST /cartoes

Request Body:

```json

{
  "numeroCartao": "1234567890123456",
  "senha": "1234"
} 
```
 - Consultar Saldo

        GET /cartoes/{numeroCartao}

### Testes
Testes Unitários
Para rodar os testes unitários, utilize o comando:

sh
Copiar código
./mvnw test