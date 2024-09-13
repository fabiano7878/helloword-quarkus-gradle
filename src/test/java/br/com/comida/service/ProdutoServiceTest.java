package br.com.comida.service;

import br.com.comida.entity.Produto;
import br.com.comida.record.ProdutoRecord;
import br.com.comida.repository.ProdutoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Nested
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllByRecord_ListaProdutoNull() {
        when(produtoRepository.listAll()).thenReturn(null);

        Response response = produtoService.getAllByRecord();

        assertEquals(500, response.getStatus());
        assertEquals("Erro ao buscar a lista de produtos!", response.getEntity());
    }

    @Test
    void testGetAllByRecord_ListaProdutoEmpty() {
        when(produtoRepository.listAll()).thenReturn(List.of());

        Response response = produtoService.getAllByRecord();

        assertEquals(200, response.getStatus());
        assertTrue(((List<?>) response.getEntity()).isEmpty());
    }

    @Test
    void testGetAllByRecord_ValidaListaProduto() {
        Produto produto1 = Produto.builder()
                .id(1L)
                .nome("Produto 1")
                .peso("1kg")
                .dataDeValidade(Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant()))
                .marca("Marca A")
                .quantidadeDisponivel(10L)
                .build();

        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Produto 2")
                .peso("500g")
                .dataDeValidade(Date.from(LocalDateTime.now().plusDays(60).atZone(ZoneId.systemDefault()).toInstant()))
                .marca("Marca B")
                .quantidadeDisponivel(5L)
                .build();

        when(produtoRepository.listAll()).thenReturn(Arrays.asList(produto1, produto2));

        Response response = produtoService.getAllByRecord();

        assertEquals(200, response.getStatus());
        List<?> produtoRecords = (List<?>) response.getEntity();
        assertEquals(2, produtoRecords.size());
    }

    @Test
    void testGetAllByRecord_HumProdutoComParametrosNull() {
        Produto produto = Produto.builder()
                .id(1L)
                .nome(null)  // Nome nulo
                .peso(null)  // Peso nulo
                .dataDeValidade(null)  // Data de validade nula
                .marca(null)  // Marca nula
                .quantidadeDisponivel(null)  // Quantidade nula
                .build();

        when(produtoRepository.listAll()).thenReturn(List.of(produto));

        assertThrows(NullPointerException.class, () -> produtoService.getAllByRecord());
    }

    @Test
    void testGetAllByRecord_ProdutoQdtZero() {
        Produto produto = Produto.builder()
                .id(1L)
                .nome("Produto com quantidade zero")
                .peso("1kg")
                .dataDeValidade(Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant()))
                .marca("Marca A")
                .quantidadeDisponivel(0L)  // Quantidade zero
                .build();

        when(produtoRepository.listAll()).thenReturn(List.of(produto));

        Response response = produtoService.getAllByRecord();

        List<?> produtoRecords = (List<?>) response.getEntity();
        assertEquals(1, produtoRecords.size());
    }

    @Test
    void testvalidaProdutoAndCria_CriarProdutoIdNullNoPayloadCriarComSucesso() {
        ProdutoRecord produto = new ProdutoRecord(null, "Macarrão n8", "500g", LocalDateTime.now(), "Barilla", 10L);
        Response response = produtoService.validaProdutoAndCria((v1) -> Objects.isNull(produto), produto);
        assertEquals(200, response.getStatus());
    }

    @Test
    void testaddProduto_addProdutoInformandoIdNoPayloadCriarComSucesso() {
        ProdutoRecord produtoRecord = new ProdutoRecord(10L, "Macarrão n8", "500g", LocalDateTime.now(), "Barilla", 10L);
        produtoService.addProduto(produtoRecord);
        Response response =  Response.ok("Produto addicionado com sucesso!").status(200).build();
        assertEquals("Produto addicionado com sucesso!", response.getEntity());
    }

    @Test
    void testvalidaProdutoAndCria_ErroAoEnviarIdDoProdutoPOeloPayload() {
      Produto produtoComIdPreencido = Produto.builder()
                .id(10L)
                .nome("Macarrão n8")
                .peso("500g")
                .dataDeValidade(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .marca("Barilla")
                .quantidadeDisponivel(10L)
                .build();

        doThrow(new EntityExistsException()).when(produtoRepository).persist(produtoComIdPreencido);
        assertThrows(EntityExistsException.class, () -> produtoRepository.persist(produtoComIdPreencido));
    }

    @Test
    void testvalidaProdutoAndCria_ProdutoEmpty() {
        Produto produtoEmpty = Produto.builder().build();
        doThrow(new NullPointerException()).when(produtoRepository).persist(produtoEmpty);
        assertThrows(NullPointerException.class, () -> produtoRepository.persist(produtoEmpty));
    }

    @Test
    void testvalidaProdutoAndCria_ProdutoNull() {
        Produto produtoNull = null;
        doThrow(new NullPointerException()).when(produtoRepository).persist(produtoNull);
        assertThrows(NullPointerException.class, () -> produtoRepository.persist(produtoNull));
    }


}