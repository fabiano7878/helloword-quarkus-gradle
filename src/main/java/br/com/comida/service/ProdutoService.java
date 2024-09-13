package br.com.comida.service;

import br.com.comida.dto.ProdutoDTO;
import br.com.comida.entity.Produto;
import br.com.comida.funcional.ValidateProdutoFuncional;
import br.com.comida.record.ProdutoRecord;
import br.com.comida.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoService {

    @Inject
    private ProdutoRepository produtoRepository;

    @Inject
    Validator validator;

    public Response validaProdutoAndCria(ValidateProdutoFuncional<ProdutoRecord> validateProdutoFuncional, ProdutoRecord recordProduto) {
        if (validateProdutoFuncional.isNullProduto(recordProduto)) {
            System.out.printf("Os dados do produtoDTO: %s são obrigatórios!", recordProduto);
            return Response.ok("Erro na solicitação de criação do novo produto!").status(400).build();
        }
        addProduto(recordProduto);
        return Response.ok("Produto addicionado com sucesso!").status(200).build();
    }

    public Response validaProdutoAndCriaWithDTO(ValidateProdutoFuncional<ProdutoDTO> validateProdutoFuncional, ProdutoDTO produtoDTO) {
        if (validateProdutoFuncional.isNullProduto(produtoDTO) || validateProdutoFuncional.isNullOrBlankMandatoryies(produtoDTO, validator)) {
            System.out.printf("Os dados do produtoDTO: %s são obrigatórios!", produtoDTO);
            return Response.ok("Erro na solicitação de criação do novo produto!").status(400).build();
        }
        addProdutoWithDTO(produtoDTO);
        return Response.ok("Produto addicionado com sucesso!").status(200).build();
    }

    public void addProduto(ProdutoRecord produtoRecord) {
        produtoRepository.persist(Produto.builder()
                .nome(produtoRecord.nome())
                .peso(produtoRecord.peso())
                .dataDeValidade(java.sql.Timestamp.valueOf(produtoRecord.dataDeValidade()))
                .marca(produtoRecord.marca())
                .quantidadeDisponivel(produtoRecord.quantidadeDisponivel())
                .build());
    }

    private void addProdutoWithDTO(ProdutoDTO produtoDTO) {
        produtoRepository.persist(Produto.builder()
                .nome(produtoDTO.getNome())
                .peso(produtoDTO.getPeso())
                .dataDeValidade(java.sql.Timestamp.valueOf(produtoDTO.getDataDeValidade()))
                .marca(produtoDTO.getMarca())
                .quantidadeDisponivel(produtoDTO.getQuantidadeDisponivel())
                .build());
    }

    public ProdutoDTO updateProduto(Produto produto, ProdutoDTO produtoDTO) {
        produto.setNome(StringUtils.isBlank(produtoDTO.getNome()) ? produto.getNome() : produtoDTO.getNome());
        produto.setPeso(StringUtils.isBlank(produtoDTO.getPeso()) ? produto.getPeso() : produtoDTO.getPeso());
        produto.setDataDeValidade(Objects.isNull(produtoDTO.getDataDeValidade()) ? produto.getDataDeValidade() : java.sql.Timestamp.valueOf(produtoDTO.getDataDeValidade()));
        produto.setMarca(StringUtils.isBlank(produtoDTO.getMarca()) ? produto.getMarca() : produtoDTO.getMarca());
        produto.setQuantidadeDisponivel(produtoDTO.getQuantidadeDisponivel() <= 0
                ? produto.getQuantidadeDisponivel() : produtoDTO.getQuantidadeDisponivel());
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .dataDeValidade(produto.getDataDeValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .peso(produto.getPeso())
                .quantidadeDisponivel(produto.getQuantidadeDisponivel())
                .build();
    }

    public Response getAll() {
        List<Produto> produtos = produtoRepository.listAll();
        if (Objects.isNull(produtos)) {
            System.out.printf("Não foi possivel acessar os registros a lista de produtos está: %s !", produtos);
            return Response.ok("Erro ao buscar a lista de produtos!").status(500).build();
        }
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        produtos.forEach(p -> {
            produtosDTO.add(ProdutoDTO.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .peso(p.getPeso())
                    .marca(p.getMarca())
                    .dataDeValidade(p.getDataDeValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .quantidadeDisponivel(p.getQuantidadeDisponivel())
                    .build());
        });
        return Response.ok(produtosDTO).status(200).build();
    }

    public Response getAllByRecord() {
        List<Produto> produtos = produtoRepository.listAll();
        if (Objects.isNull(produtos)) {
            System.out.printf("Não foi possivel acessar os registros a lista de produtos está: %s !", produtos);
            return Response.ok("Erro ao buscar a lista de produtos!").status(500).build();
        }

        List<ProdutoRecord> produtoRecords = produtos.stream().map(p -> new ProdutoRecord(p.getId(),
                p.getNome(),
                p.getPeso(),
                p.getDataDeValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                p.getMarca(),
                p.getQuantidadeDisponivel())).toList();

        return Response.ok(produtoRecords).status(200).build();
    }

    public Response update(Long id,ProdutoDTO produtoDTO) {
        produtoDTO.setId(id);
        return validateAndUpdate((v1) -> Objects.nonNull(v1), produtoDTO);
    }

    public Response validateAndUpdate(ValidateProdutoFuncional validateProdutoFuncional, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(produtoDTO.getId());
        if (validateProdutoFuncional.isNullProduto(produto)) {
            System.out.printf("Produto não identificado: %s", produto);
            return Response.noContent().build();
        }
        return Response.ok("Atualização realizada com Sucesso! \n" + updateProduto(produto, produtoDTO)).status(201).build();
    }

    public Response delete(Long id) {
        boolean deleted = produtoRepository.deleteById(id);
        return Response.ok(String.format("Delete o produto de id: %s foi deletado? %s ", id, deleted)).status(200).build();
    }

}
