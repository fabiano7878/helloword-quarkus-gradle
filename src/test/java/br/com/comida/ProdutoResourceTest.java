package br.com.comida;

import br.com.comida.dto.ProdutoDTO;
import com.google.common.collect.Lists;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ProdutoResourceTest {
    @Test
    void testCreateEndpoint() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .nome("Teste 1")
                .peso("10g")
                .dataDeValidade(LocalDateTime.now())
                .build();
        given()
                .contentType("application/json")
                .body(produtoDTO)
                .when().post("/produto")
                .then()
                .statusCode(200)
                .body(is("Produto addicionado com sucesso!"));
    }

    @Test
    void testCreateEndpointWithInvalidData() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .nome("")  // Nome inválido
                .peso("10g")
                .dataDeValidade(LocalDateTime.now())
                .build();
        given()
                .contentType("application/json")
                .body(produtoDTO)
                .when().post("/produto")
                .then()
                .statusCode(400)
                .body(is("Erro na solicitação de criação do novo produto!"));
    }

    @Test
    void testGetAllEmptyListEndpoint() {
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        given()
                .when().get("/produto")
                .then()
                .statusCode(200)
                .body(is(produtosDTO.toString()));
    }


}

