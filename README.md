# Central de Erros
API REST para centralizar logs de erros. 
Tecnologias utilizadas: Spring, Java, Hibernate ORM com Mariadb, Spring Fox, Swagger, JWT.

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

