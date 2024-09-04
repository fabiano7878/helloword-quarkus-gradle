# helloword-quarkus-gradle
Projeto com Quarkus, java, ambiente com GralVM, Panache, lombok

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-comida-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)




## Sobre

- API CRUD

Referencias na documentação official e tutorial abaixo:

Documentação oficial
https://pt.quarkus.io/guides/rest-json

Gerar o projeto Oficial
https://code.quarkus.io/

Exemplo de uso:
https://marcus-paulo.medium.com/tutorial-criando-um-crud-utilizando-quarkus-java-rest-cdi-panache-hibernate-com-postgres-59793e0d7162

https://medium.com/@manollo.guedes11/rest-api-using-quarkus-and-panache-79091228c187

https://blog.sebastian-daschner.com/entries/quarkus-panache-active-record-vs-repository-pattern