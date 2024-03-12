# App Contatos
### Esta é uma REST API desenvolvida em Java, utilizando Spring Boot que controla um cadastro de Pessoas e seus contatos

### Principais tecnologias/bibliotecas utilizadas
- Spring Boot
- Maven
- Banco de dados AWS RDS PostgreSql 
- JPA 
- NHibernate
- Swagger
- Autenticação JWT

## EXECUÇÃO DA APLICAÇÃO
1. Um banco de dados PostgreSql está disponível e ativo na AWS, pronto para uso e com dados, dispensando a necessidade de instalar um banco de dados local. Esse banco ficará ativo até o dia 15/03/2024. Após isso, em application.properties habilite a opção para usar um banco local postgres ou h2.
2. Clonar o projeto, abrir o mesmo com um editor de texto como o VSCode.
3. Baixar dependências via Maven.
4. Se utilizar o VSCode, como eu, use a extensão "Extension Pack for Java" e "Spring Initializr Java Support" e execute a aplicação via ícone "Spring Boot Dashboard"; ou use outra IDE de sua preferência para isso.
   
## UTILIZAÇÃO
1. Você poderá acessar a API sua documentação Swagger no endpoint *[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)*
2. Para utilizção obtenha primeiro um token de acesso em TOKEN "/token". É necessário apenas digitar um nome de usuário qualquer, por exemplo: "José";
3. Copie o token retornado e cole no topo da página clicando no cadeado "Authorize";
4. Cole o token no campo e clique em "Authorize";
5. Após isso você pode realizar as consultas diretamente na documentação Swagger seguindo as orientações de cada rota.
6. Se você for utilizar o Postman ou o Insomnia acesse as rotas em *[http://localhost:8080/](http://localhost:8080/)* e o restante do caminho descrito no Swagger junto com seus parâmetros

## FACILITADORES PARA CONSULTAS
### Exemplo de envio de objeto, payload, para rota de CRIAR Pessoa [POST]"api/pessoas"
<pre>
    {
        "id": 11,
        "nome": "Hans Muller",
        "endereco": "Alameda Gaivotas, 468",
        "cep": "05.123-050",
        "cidade": "Barueri",
        "uf": "SP",
        "contatos": [
            {
                "id": 27,
                "tipoContato": "Celular",
                "contato": "(11) 95436-2563"
            }
        ]
    }
</pre>

---

### Exemplo de envio de objeto, payload, para rota de ATUALIZAR Pessoa [PUT]"api/pessoas/id"

<pre>
{
  "id": 0,
  "nome": "string",
  "endereco": "string",
  "cep": "string",
  "cidade": "string",
  "uf": "string",
  "contatos": [
    {
      "id": 0,
      "tipoContato": "Telefone" ou "Celular" ou "Email" ou "RedeSocial",
      "contato": "string"
    }
  ]
}
</pre>

--- 

### Exemplo de envio de objeto, payload, para rota de ADICIONAR NOVO CONTATO a uma Pessoas [POST]"api/pessoas/id/contatos"
<pre>
{
  "id": 0,
  "tipoContato": "Telefone" ou "Celular" ou "Email" ou "RedeSocial",
  "contato": "(11) 4444-4444",
}
</pre>

--- 

### Exemplo de envio de objeto, payload, para rota de ATUALIZAR um Contato [PUT]"api/contatos"
<pre>
{
  "id": 27,
  "tipoContato": "Telefone",
  "contato": "(11) 4444-4444",
}
</pre>

