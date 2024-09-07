# helloword-quarkus-gradle
Projeto com as tecnologias 
- Quarkus, Panache
- lombok
- java version "21.0.4" 2024-07-16 LTS
- Java(TM) SE Runtime Environment Oracle GraalVM 21.0.4+8.1 (build 21.0.4+8-LTS-jvmci-23.1-b41)
- Gradle 8.10

## Sobre o projeto

- API CRUD
Aplicação tem o objetivo de cadastrar/Consultar/Alterar/Remover refeições, que eu chamo de "Comida" com algumas informações epecificas, em especial é possível vincular os produtos que serão usado no preparo.
Também é possivel cadastrar/Consultar/Alterar/Remover os produtos que são usados no preparo das comidas.

### Observção Sobre o update do Panache.
Um atenção aqui com a função de atualização(update), no Quarkus com Panache é bem estranho, para quem vem do spring, onde ao final do processo a gente invocar o "save" para realizar as mudanças, aqui ao alterar o estado do objeto, os dados são persistidos na base.
eu vi tmabém que em alguns casos os modificadores do seu modelo devem estar public, porém no meu caso aqui eu usei como private mas tenho o auxilio das anotações do lombok, que me fornece os membros de acesso.


##Teste do endpoint, parse de LocalDateTime precisei usar abordagem a mais para conseguir sucesso.
Foi preciso anotar a variavel de data no meu DTO como o "JsonFormat" do pacote "com.fasterxml.jackson.annotation". 

## API Produtos
path = /produto
GET = consulta a lista de produtos cadastrados.
POST = cria um produto novo, usando Record como payload.  
/dto = cria um produto novo, com DTO e o Validator da especificação Jakarta.
PATCH /{id} = atualiza um registro existente.
DELETE /{id} = remove um registro existente.

exemplo das urls
POST = Localhost:8080/produto
POST = Localhost:8080/produto/dto
PATCH = Localhost:8080/produto/{id}

Obs: A motiviação por cirar 2 endpints da mesma função é testar o uso do Record no primeiro caso e o Validator do Jakarta, recursos novos que estão na mais rescente versão do Java.

## API Comida
path = /comida
POST = cria um comida nova, com DTO e o Validator da especificação Jakarta.

### Relacionamento das Entidades
Comida
Nesta entidade eu preiso ter uma lista de criei para receber a lista de ingredientes da comida, então eu precisei ter um relacionamento de Um pra muitos (OneToMany)
E no caso eu defino que esse relacionamento esta na entidade "ComidaProduto" é assim que ele vai encontrar o relacionamento do produto com a comida.
mapiei a lista com o relacionamento a entidade ComidaProduto (variavel = List<ComidaProduto> ingredientes). Veja classe da entidade para epgar os detalhes da anotação.

ComidaProduto
E ai pensando em como eu ia criar essa referência, tive alguma dificuldade, mas consgui entender que 1 produto pode estar relacionado com N comidas e o mesmo acontece para comida, 1 comida pode estar relacionada com N produtos.
declarei as variaveis para armazenar o id de comida e o id de produto e em ambos as anotações "ManyToOne"

Produto
Ficou com as anotações de Coluna, sempre cisar de relacionamentos adicionais entre as entidades acima.



##### Referencias na documentação official e tutorial abaixo:

Documentação oficial
https://pt.quarkus.io/guides/rest-json

Gerar o projeto Oficial
https://code.quarkus.io/

Exemplo de uso:
https://marcus-paulo.medium.com/tutorial-criando-um-crud-utilizando-quarkus-java-rest-cdi-panache-hibernate-com-postgres-59793e0d7162

https://medium.com/@manollo.guedes11/rest-api-using-quarkus-and-panache-79091228c187

https://pw2.rpmhub.dev/topicos/jpa/hibernate.html

Referência para os testes:
https://github.com/rest-assured/rest-assured/wiki/Usage#example-1---json

https://medium.com/@andylke/rest-controller-configure-date-time-format-in-json-response-201e97aa74b0

Referência protocolos http:
https://developer.mozilla.org/en-US/docs/Web/HTTP/Status