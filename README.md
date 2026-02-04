# 🍽️ Restaurant API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

API RESTful completa para gerenciamento de reservas de restaurante, desenvolvida com Java e Spring Boot. O projeto inclui controle de autenticação/autorização com JWT, regras de negócio complexas para agendamento e infraestrutura containerizada.

## 🚀 Funcionalidades

- **Autenticação e Segurança:**
  - Registro e Login de usuários.
  - Autenticação via Token JWT (JSON Web Token).
  - Controle de acesso por perfis (Admin/Client).
- **Gestão de Mesas:**
  - CRUD de mesas (Criação e listagem).
  - Controle de capacidade.
- **Gestão de Reservas:**
  - Criação de reservas com validação de horário.
  - **Regra de Overbooking:** O sistema impede reservas na mesma mesa em horários conflitantes (janela de tempo).
  - **Regra de Capacidade:** Validação se o número de pessoas cabe na mesa selecionada.
- **Documentação:**
  - Interface Swagger UI para testes e visualização dos endpoints.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Framework:** Spring Boot 3.4
- **Segurança:** Spring Security + Auth0 (JWT)
- **Banco de Dados:** - H2 Database (Ambiente de Testes/Memória)
  - PostgreSQL (Ambiente de Desenvolvimento/Produção)
- **Infraestrutura:** Docker & Docker Compose
- **Testes:** JUnit 5 + Mockito
- **Documentação:** SpringDoc OpenAPI (Swagger)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas (N-Tier) focada na separação de responsabilidades:
- **Controller:** Camada REST que recebe as requisições.
- **Service:** Regras de negócio e validações.
- **Repository:** Acesso a dados com Spring Data JPA.
- **DTOs:** Objetos de transferência de dados para segurança e desacoplamento.
