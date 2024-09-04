package br.com.comida.service;

import br.com.comida.dto.ProdutoDTO;
import br.com.comida.entity.Produto;
import br.com.comida.funcional.ValidateProdutoFuncional;
import br.com.comida.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProdutoService {

    @Inject
    private ProdutoRepository produtoRepository;

    public Response createProduto(ProdutoDTO produtoDTO) {
        return validaProdutoAndCria((v1) -> StringUtils.isBlank(produtoDTO.getNome()),produtoDTO);
    }

    private Response validaProdutoAndCria(ValidateProdutoFuncional validateProdutoFuncional, ProdutoDTO produtoDTO) {
        boolean isNameNullorEmpty = Objects.isNull(produtoDTO) ? true : validateProdutoFuncional.nameNullorBlank(produtoDTO.getNome());
        if(isNameNullorEmpty){
            System.out.printf("Os dados do produtoDTO: %s são obrigatórios!", produtoDTO.toString());
            return Response.ok().status(403).build();
        }
        addProduto(produtoDTO);
        return Response.ok("Produto addicionado com sucesso!").status(200).build();
    }

    public void addProduto(ProdutoDTO produtoDTO){
        produtoRepository.persist(Produto.builder()
                .nome(produtoDTO.getNome())
                .peso(produtoDTO.getPeso())
                .dataDeValidade(java.sql.Timestamp.valueOf(produtoDTO.getDataDeValidade()))
                .build());
    }

    public void updateProduto(Long id, Produto produto){
        produtoRepository.update(id, produto);
    }

    public Response getAll(){
        List<Produto> produtos = produtoRepository.listAll();
        if (Objects.isNull(produtos)) {
        System.out.printf("Registros não encontrados: %s !", produtos);
            return Response.ok().status(400).build();
        }
            List<ProdutoDTO> produtosDTO = new ArrayList<>();
            produtos.stream().forEachOrdered(p -> {
                produtosDTO.add(ProdutoDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .peso(p.getPeso())
                        .dataDeValidade(p.getDataDeValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .build());
            });
            return Response.ok(produtosDTO).status(200).build();
    }


    public Response update(Long id,ProdutoDTO produtoDTO){
        produtoDTO.setId(id);
        return validateAndUpdate((v1)-> Objects.nonNull(v1), produtoDTO);
    }

    public Response validateAndUpdate(ValidateProdutoFuncional validateProdutoFuncional, ProdutoDTO produtoDTO){
        Produto produto = produtoRepository.findById(produtoDTO.getId());
        if(validateProdutoFuncional.isNull(produto)){
            System.out.printf("Produto não identificado: %s",produto.getId());
            return Response.noContent().build();
        }
        updateProduto(produto, produtoDTO);
        return Response.ok("Atualização realizada com Sucesso!").status(201).build();
    }
}
