# Central de Erros
<p>API REST para centralizar erros. Projeto prático do programa acelera dev de Java da Codenation. </p>
<p>Tecnologias utilizadas: Spring, Java 11, Hibernate ORM com Mariadb, Spring Fox, Swagger, JWT. </p>
<p>Configurações relacionadas ao banco de dados (pode ser trocado para PostgreSQL, ou qualquer outro compatível com Hibernate) e à aplicação podem ser alteradas em "src/main/resources/application.properties". </p> 
# Endpoints da API
<p>Todas as entradas e saidas usam o formato JSON.</p>
<p>Para abrir a documentação completa da API /swagger-ui.html</p>

<pre>
  <code>
  
    /usuario/cadastrar
      POST / - Cadastra um usuário no sistema.

    /ususario/excluir
      DELETE / - Deleta um usuário existente.
  
    /usuario/token
      POST / - Solicita um token de acesso para o servidor.

    /logs
      GET / - Consulta todos os logs que já foram salvos. Esse endpoint suporta filtragem por atributos e configurações de paginação na url de requisição (veja a documentação em /swagger-ui.html).

    /logs
      POST / - Salva um novo registro de erro.
  
    /logs/{id}
      GET / - Consulta um registro de erro com base no id.
  
  </code>
</pre>

