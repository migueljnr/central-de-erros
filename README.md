# Central de Erros

API REST para centralizar logs de erros. 
Tecnologias utilizadas: Spring, Java, Hibernate ORM com Mariadb, Spring Fox, Swagger, JWT.

# Endpoints da API

Todas as entradas e saidas usam o formato JSON.
Para abrir a documentação completa da API /swagger-ui.html

/usuario/cadastrar
<p> *POST* / - Cadastra um usuário no sistema.</p>

/ususario/excluir
<p>*DELETE* / - Deleta um usuário existente.</p>
  
/usuario/token
 <p>*POST* / - Solicita um token de acesso para o servidor.</p>

/logs
<p>*GET* / - Consulta todos os logs que já foram salvos. Esse endpoint suporta filtragem por atributos e configurações de paginação
  na url de requisição (veja a documentação em /swagger-ui.html).</p>

/logs
<p>*POST* / - Salva um novo registro de erro.</p>
  
/logs/{id}
<p>*GET* / - Consulta um registro de erro com base no id.</p>

