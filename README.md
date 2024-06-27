<span id="top"></span>
<h1 align="center">
  <!-- <img src="https://user-images.githubusercontent.com/13087389/126053559-d4c7d080-0ad3-4deb-83dd-2a52b209e5f2.png" width="128" /> -->
  <br> Api-Sistema Bancário - Copilot



</h1>

O projeto é um sistema de web services desenvolvido com Spring Boot e JPA/Hibernate para um Sistema Bancário, ele inclui as camadas de usuário, conta e transação. O projeto utiliza o banco de dados H2 para guardar informações e pode ser facilmente integrado com outros bancos de dados relacionais.

## Status do Projeto

✅ Concluído 

## 🧩 Features
- [x] Cadastro de usuários
- [x] Listagem de usuários
- [x] Busca de usuários por ID
- [x] Atualização de usuários
- [x] Remoção de usuários
- [x] Listagem de contas
- [x] Busca de contas por ID
- [x] Cadastro de contas
- [x] Listagem de transação
- [x] Listagem de transação por ID
- [x] Fazer saque
- [x] Fazer Depósito
- [x] Fazer Transferência


## Diagramas:
- Diagrama de classe
  - Ilustrar os objetos que compõem o sistema.
  ![Frame 1](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/b62e0954-fa4c-49da-ab71-c57b8a5f187b)

- Diagrama de Caso de uso
  - Ilustrar o comportamento do sistema.
  ![Frame 2](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/66294265-374c-485e-8e88-97a54c4a7282)

- Diagrama de Sequência
  - Modelar a interação entre as diversas classes identificadas.
  - Fluxo de transferência:
    
    ![diagramaSequencial-transferencia drawio](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/c06aced3-07d9-4586-8dba-c1f08305b705)

  - Fluxo de saque:
    
    ![diagramaSequencial-saque drawio (1)](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/951e8d07-2e4c-428b-b219-00701dd37c74)

  - Fluxo de depósito:
    
    ![diagramaSequencial-deposito drawio](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/c13c5294-08c7-48fb-a5df-9fcdd4410b0d)



# Características:
- **Destaca-se**
  - 💼 Estruturação clara em camadas (resource, service, repository)
  - 🗂️ Organização eficiente dos componentes da aplicação
  - 📑 Separação de responsabilidades para facilitar a manutenção e evolução do código
  - ⚙️ Configuração simplificada e rápida do ambiente de desenvolvimento
  - 🧩 Representação precisa das entidades de negócio
  - 🏗️ Facilidade na criação de novos objetos e relacionamentos
  - 🚨 Retorno de mensagens de erro claras e significativas para o usuário final

- **Tecnologias**:
  - ☕️ Java 17
  - 🌱 Spring Framework
  - 🗃️ H2 Database
  - 🐙 Git e GitHub
  - 📜 Swagger 

<br/>

## Pré-requisitos:
- Instalar o java 17 -> https://www.oracle.com/java/technologies/downloads/#java17
- Instalar o git (opcional) -> https://git-scm.com/downloads
  
## Como usar:
 - Clonar o repositório pelo git bash
 - Rodar o comando: "java -jar sistemaBanco.jar"


## Ferramenta de teste

- Postman: é uma ferramenta de teste e colaboração de API que permite aos desenvolvedores criar, testar, documentar e compartilhar APIs com eficiência. https://www.postman.com/
  

## Padrões de projetos utilizados
- Specification

  ![Group 1](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/9c6f9816-b6ce-4d01-b869-3a232702d24d)

- Dto

  ![Group 2](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/837ad1f2-d541-471b-bbd1-c349998b4f8a)

- Builder

  ![Group 4](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/f6c031e5-16ca-41da-a648-1445a91dc55d)

  usando o padrão:
  
  ![image 11](https://github.com/RafaCavalcant1/Sistema-Bancario/assets/149629955/02f644be-55a7-4490-9b84-0a4f973fd785)



## Recursos para aprender Spring Boot

- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/ - toda a documentação do Spring Boot

