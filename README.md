# Central de Erros
<p>API REST para centralizar erros. Projeto prático do programa Acelera Dev de Java, da Codenation. </p>
<p>Tecnologias utilizadas: Spring, Java 11, Maven, Hibernate ORM com Mariadb, Spring Fox, Swagger, JWT. </p>
<p>Configurações relacionadas ao banco de dados (pode ser trocado para PostgreSQL, ou qualquer outro compatível com Hibernate) e à aplicação podem ser alteradas em "src/main/resources/application.properties". </p> 

# Teste

<p>Versão empacotada com banco h2 e alguns registros. Um usuário padrão (username : usuario, password : 1234567) foi criado, mas outros usuários poderão ser criados
conforme a documentação da api. </p>
<p>Requerimentos: Java 11 ou versão superior. </p>
<p> Baixe : https://drive.google.com/file/d/1D1lcvdTbKUHoWhIILGpCD4nmuc3eaLX6/view?usp=sharing e execute java -jar central_de_erros.</p>
<p>Aplicação no Heroku: https://centralderros.herokuapp.com/</p>


# Endpoints da API
<p>Todas as entradas e saidas usam o formato JSON.</p>
<p>Para abrir a documentação completa da API, execute a aplicação e navegue até localhost:8080/swagger-ui.html</p>

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

