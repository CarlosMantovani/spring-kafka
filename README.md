


# Car Post API

Esta API é uma aplicação Spring Boot que permite a publicação, consulta, atualização e remoção de informações sobre carros à venda, gerenciamento de proprietários e análise de dados. Ela se integra com o Apache Kafka para comunicação assíncrona e utiliza o Zookeeper para gerenciamento de brokers Kafka.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção de aplicações Java.
- **Apache Kafka**: Sistema de mensageria utilizado para comunicação assíncrona.
- **Zookeeper**: Gerenciador de configuração e coordenação para Kafka.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar dados.
- **Docker**: Para criação e gerenciamento dos containers.

## Pré-requisitos

- JDK 11 ou superior
- Docker e Docker Compose instalados

## Estrutura do Projeto

A API é dividida em três microserviços principais:

1. **API Car**: Gerencia informações sobre carros à venda.
2. **API de Análise de Dados**: Fornece dados analíticos sobre marcas, modelos e preços dos carros.
3. **API de Proprietários**: Gerencia informações sobre os proprietários dos carros.

## Configuração do Banco de Dados PostgreSQL

Para rodar a aplicação, você precisa de um banco de dados PostgreSQL. Você pode usar Docker para criar um container do PostgreSQL. A senha padrão é `1234` e o usuário é `postgres`. Os bancos de dados `car_post_storage` e `car_post_analytics` devem ser criados.

### Docker Compose

O arquivo `docker-compose.yml` para configurar PostgreSQL, Kafka e Zookeeper é o seguinte:

```yaml
version: '3.8'

services:
  zookeeper:
    image: bitnami/zookeeper:latest
    ports:
      - "2181:2181"
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes

  kafka:
    image: bitnami/kafka:latest
    restart: on-failure
    ports:
      - "9092:9092"
    environment:
      - KAFKA_CFG_BROKER_ID=1
      - KAFKA_CFG_LISTENERS=PLAINTEXT://0.0.0.0:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
      - KAFKA_CFG_NUM_PARTITIONS=3
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper

  postgres:
    image: postgres:latest
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1234
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  kafka-ui:
    image: provectuslabs/kafka-ui
    container_name: kafka-ui
    depends_on:
      - kafka
      - zookeeper
    ports:
      - "8080:8080"
    environment:
      - KAFKA_CLUSTERS_0_NAME=DataWayBR
      - KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:9092
      - KAFKA_CLUSTERS_0_ZOOKEEPER=zookeeper:2181

volumes:
  postgres_data:
```

## Execução

Para executar a API e os microserviços, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/CarlosMantovani/spring-kafka.git
   cd spring-kafka
   ```

2. Inicie os serviços do Docker:
   ```bash
   docker-compose up -d
   ```

3. Após o PostgreSQL iniciar, conecte-se ao banco de dados e crie os bancos de dados:
   ```bash
   docker exec -it <nome_do_container_postgres> psql -U postgres
   ```

   Em seguida, execute os comandos SQL:
   ```sql
   CREATE DATABASE car_post_storage;
   CREATE DATABASE car_post_analytics;
   ```

4. Execute a aplicação Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

## Endpoints

### Carros

- **POST** `/api/car/post`
  - **Descrição**: Cria um novo carro à venda.
  - **Body**:
    ```json
    {
      "model": "Corolla",
      "brand": "Toyota",
      "price": 10000,
      "description": "semi-novo",
      "engineVersion": "2.0",
      "city": "Sao paulo",
      "createdDate": "21/12/22",
      "ownerId": "1",
      "ownerName": "Fulano",
      "ownerType": "Profissional",
      "contact": "222-222-222"
    }
    ```

- **GET** `/api/car/brands`
  - **Descrição**: Obtém a lista de marcas de carros.
  - **Response**:
    ```json
    [
      // lista de marcas
    ]
    ```

- **GET** `/api/car/model`
  - **Descrição**: Obtém a lista de modelos de carros.
  - **Response**:
    ```json
    [
      // lista de modelos
    ]
    ```
- **GET** `/api/car/price`
  - **Descrição**: Obtém dados analíticos sobre preços de carros.
  - **Response**:
    ```json
    [
      // lista de modelos
    ]
    ```

- **GET** `/api/car/posts`
  - **Descrição**: Obtém a lista de carros à venda.
  - **Response**:
    ```json
    [
      // lista de carros
    ]
    ```

- **PUT** `/api/car/{id}`
  - **Descrição**: Atualiza as informações de um carro à venda.
  - **Path Variable**: `id` - ID do carro.
  - **Body**:
    ```json
    {
      "description": "USADO",
      "contact": "333-3332",
      "price": 21,
      "brand": "FORD",
      "engineVersion": "2.2",
      "model": "FUSION"
    }
    ```

- **DELETE** `/api/car/{id}`
  - **Descrição**: Remove um carro à venda.
  - **Path Variable**: `id` - ID do carro.

### Proprietários

- **POST** `/owner`
  - **Descrição**: Cria um novo proprietário.
  - **Body**:
    ```json
    {
      "name": "Carlos",
      "type": "Amador",
      "contactNumber": "222-222-22"
    }
    ```


## Postman Collection

Você pode importar a coleção Postman para facilitar os testes da API. 

## Conclusão

Esta API fornece uma solução completa para gerenciar a venda de carros e seus proprietários, além de oferecer análises de dados valiosos. Você pode usar a coleção do Postman para testar todos os endpoints e verificar se tudo está funcionando corretamente.

