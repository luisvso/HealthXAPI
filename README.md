HealthX API

API REST para gerenciamento de pacientes, psicólogos e sessões de terapia, desenvolvida com foco em organização, segurança e boas práticas de engenharia de software.

Este projeto faz parte do ecossistema HealthX, cujo objetivo é facilitar o controle de atendimentos terapêuticos por meio de uma aplicação moderna, escalável e bem estruturada.

🧠 Visão Geral
A HealthX API é responsável por:
Cadastro e autenticação de psicólogos
Cadastro e gerenciamento de pacientes
Criação e controle de sessões de terapia
Aplicação de regras de negócio
Exposição de endpoints REST para consumo por um frontend (ex: Angular)

🚀 Tecnologias Utilizadas
Java
Spring Boot
Spring Web (REST APIs)
Spring Data JPA
Spring Security (autenticação e autorização)
Hibernate
Banco de Dados Relacional (ex: PostgreSQL)
Maven


🔐 Segurança

A API utiliza Spring Security, garantindo:

Autenticação via login

Proteção de endpoints sensíveis

Separação de responsabilidades entre usuários

Apenas usuários autenticados podem acessar recursos protegidos.

📚 Principais Funcionalidades
👩‍⚕️ Psicólogos
Cadastro
Login
Atualização de dados

🧑 Pacientes
Cadastro
Listagem
Atualização
Remoção

📅 Sessões de Terapia
Criação de sessão
Associação entre psicólogo e paciente

Listagem de sessões


📈 Próximas Melhorias
Integração com Swagger (OpenAPI)
Logs estruturados
Monitoramento e métricas
Deploy em ambiente cloud

Atualização e cancelamento
