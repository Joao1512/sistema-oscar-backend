# S1STEM - FRONTEND - OSCAR

# 🎯 Objetivo

Desenvolver uma aplicação para a loja Oscar Calçados. Deve ser possível administrar um estoque de sapatos criando, editando, excluindo e listando-os com filtros.

Extra: Deve ser possível criar diferentes perfis de usuário e gerenciar suas permissões nas funcionalidades do sistema.

O sistema possui autenticação/autorização com *token JWT* e protocolo *Oauth2.*

# Demonstração

# 💻 Tecnologias

- **Frontend**
    
    Angular 8+
    
    TypeScript
    
    Angular Material CSS
    
- **Backend**
    - Java 11
    - SpringBoot Framework
    - Hibernate
    - Liquibase
    - PostgreSQL
    - Swagger
    - JWT
    - Protocolo Oauth2

# Passos para Iniciar

## 🅰️ **FRONTEND**

Pré-requisitos: ter instalado o node, npm e o angular-cli .v11

### Clonando o repositório

REPO_FRONT

### Instalando as dependências

Pelo terminal, vá até o diretório do repositório e digite: 

```jsx
npm install
```

### Inicializando o servidor

Digite o comando:

```jsx
ng serve
```

Veja a aplicação através do navegador na URL:

```jsx
localhost:4200
```

---

- ⚠️ Credenciais de Administrador:
    - Email: admin@email.com
    - senha: admin

## ☕ BACKEND

Pré-requisitos: ter instalado o PostgreSQL 12 juntamente com o PgAdmin 4.

Rodar o Projeto Java com o [SpringToolSuite](https://spring.io/tools) para Eclipse.

### 💾 Configurando o banco de dados

1. Abra o pgAdmin, caso o instalador peça um login e senha, use postgres para login e root para a senha.
2. Clique em *Add New Server* e nomeie para “sistem01”.
3. Clique em Connection, nomeie o Host Name para “localhost”, port “5432”, username “postgres”.

### Clonando o repositório

REPO_BACK

### ☕ Configurando o Projeto Java

1. Abra o SpringToolSuite, vá em File > Import... > Existing Maven Project
2. Como todo projeto Maven, é necessário rodar o comando *mvn clean install.* Você pode fazer isso clicando com o botão direito no projeto importado > run as > maven clean e maven install

### 🌶️ Configurando Lombok

1. Faça o [download](https://projectlombok.org/download).
2. Abra o executável e configure para que ele procure dentro do diretório onde o SpringToolSuite foi instalado.

### ☕ Inicializando a Aplicação

Dentro da IDE, clique com o botão direito no projeto > run as > spring boot app

Se tudo correu bem, você deve ser capaz de visualizar a documentação da API em:

[http://localhost:8090/swagger-ui.html#/](http://localhost:8090/swagger-ui.html#/)

---

## Como testar

⚠️ Credenciais de Administrador:

- Email: admin@email.com
- senha: admin

### Gerando Token de autenticação

- Use o Postman ou algo similar e importe a seguinte cURL:

```jsx
curl --location --request POST 'http://localhost:8090/oauth/token?grant_type=password&username=admin@email.com&password=admin' \
--header 'Authorization: Basic Y2xpZW50OjEyMzQ1' \
--data-raw ''
```

## Testando Lista de Sapatos

- Para verificar a lista de sapatos, importe a cURL abaixo substituindo o “*MEU_TOKEN*” pelo token gerado no passo anterior.

```jsx
curl --location --request POST 'http://localhost:8090/sapatos/listarFiltrado' \
--header 'Authorization: Bearer MEU_TOKEN' \
--header 'Content-Type: application/json' \
--data-raw '{

}'
```