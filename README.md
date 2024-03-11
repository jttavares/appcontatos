# App Contatos
### Esta é uma REST API desenvolvida em Java, utilizando Spring Boot que controla um cadastro de Pessoas e seus contatos

### Principais tecnologias/bibliotecas utilizadas
- Spring Boot
- Maven
- Base de dados PostgreSql
- jpa nhibernate
- Swagger
- Autenticação JWT

## EXECUÇÃO DA APLICAÇÃO
1. Instalar banco de dados PostgreSql
2. Clonar o projeto
3. Baixar dependências via Maven
4. Se utilizar o VSCode como eu use a extensão "Extension Pack for Java" e "Spring Initializr Java Support" e execute a aplicação ou use outra IDE de sua preferência para isso.
   
## UTILIZAÇÃO
1. Você poderá acessar a API sua documentação Swagger no endpoint *[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)*
2. Para utilizção obtenha primeiro um token de acesso em TOKEN "/token"
3. Copie o token retornado e cole no topo da página clicando no cadeado "Authorize"
4. Cole o token no campo e clique em Login
5. Após isso você pode realizar as consultas diretamente na documentação Swagger seguindo as orientações de cada rota.
6. Se você for utilizar o Postman ou o Insomnia acesse as rotas em *[http://localhost:8080/](http://localhost:8080/)* e o restante do caminho descrito no Swagger junto com seus parâmetros
   

