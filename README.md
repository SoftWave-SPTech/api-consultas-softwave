# API Consultas - Microserviço de Consultas Jurídicas

Microserviço especializado em consultas processuais e integração com APIs externas para busca de informações jurídicas.

## Tecnologias Utilizadas

![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

### Dependências Principais

- **Spring Boot 3.3.3** - Framework principal
- **Spring Web** - APIs REST
- **Spring AMQP** - Integração com RabbitMQ
- **JSON** - Manipulação de dados JSON

## Requisitos do Sistema

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

- **Java** >= 21
- **Maven** >= 3.8.0
- **RabbitMQ** >= 3.8

## Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone <repository-url>
cd api-consultas-softwave
```

### 2. Configuração RabbitMQ

```bash
# Via Docker
docker run -d --hostname rabbitmq --name rabbitmq-server \
  -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=softwave \
  -e RABBITMQ_DEFAULT_PASS=softwave123 \
  rabbitmq:3-management
```

### 3. Configuração de Ambiente

```yaml
spring:
  application:
    name: consultas-service

# Servidor
server:
  port: 8084

# RabbitMQ Configuration
rabbitmq:
  host: ${RABBITMQ_HOST:localhost}
  port: ${RABBITMQ_PORT:5672}
  username: ${RABBITMQ_USERNAME:softwave}
  password: ${RABBITMQ_PASSWORD:softwave123}
  exchange: ${RABBITMQ_EXCHANGE:processos.exchange}
  queue: ${RABBITMQ_QUEUE:processos.queue}
  routing-key: ${RABBITMQ_ROUTING_KEY:processos.key}
```

### 4. Variáveis de Ambiente

```bash
export RABBITMQ_HOST=localhost
export RABBITMQ_USERNAME=softwave
export RABBITMQ_PASSWORD=softwave123
```

### 5. Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8084

## Endpoints da API

### Consultas Processuais

#### GET /api/consultas/processo/{numero}
Consulta processo por número

#### POST /api/consultas/lote
Consulta múltiplos processos

#### GET /api/consultas/tribunal/{tribunal}/processo/{numero}
Consulta processo em tribunal específico

## Integração RabbitMQ

### Filas Configuradas

- **processos.exchange**: Exchange principal
- **processos.queue**: Fila de processamento
- **processos.dlq**: Dead Letter Queue

### Mensagens Processadas

```json
{
  "tipo": "CONSULTA_PROCESSO",
  "numeroProcesso": "1234567-89.2025.8.26.0001",
  "tribunal": "TJSP",
  "solicitante": "user@example.com"
}
```

## Troubleshooting

1. **RabbitMQ não conecta**: Verifique se o serviço está rodando
2. **Fila não existe**: Verifique configuração de exchanges e filas
3. **Timeout consultas**: Ajuste timeout das APIs externas

---

**Desenvolvido por:** SoftWave SPTech