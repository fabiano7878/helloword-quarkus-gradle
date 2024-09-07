package br.com.comida.service;

import br.com.comida.dto.ComidaDTO;
import br.com.comida.entity.Comida;
import br.com.comida.entity.ComidaProduto;
import br.com.comida.entity.ComidaProdutoPersonalizacao;
import br.com.comida.entity.Produto;
import br.com.comida.funcional.ValidateComidaFuncional;
import br.com.comida.repository.ComidaRepository;
import br.com.comida.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ComidaService {


    @Inject
    Validator validator;

    @Inject
    ComidaRepository comidaRepository;

    @Inject
    ProdutoRepository produtoRepository;

    public Response validaComidaeCria(ValidateComidaFuncional validateComidaFuncional, ComidaDTO comidaDTO) {
        if(validateComidaFuncional.isNullComida(comidaDTO) || validateComidaFuncional.isNullOrBlankMandatoryies(comidaDTO, validator)){
            System.out.printf("Os dados do comidaDTO: %s são obrigatórios!", comidaDTO);
            return Response.ok("Erro na solicitação de criação do nova comida!").status(400).build();
        }
        addComida(comidaDTO);
        return Response.ok("A comida foi addicionada com sucesso!").status(200).build();
    }

    private void addComida(ComidaDTO comidaDTO) {
         comidaRepository.persist(buildComida(comidaDTO));
    }

    private Comida buildComida(ComidaDTO comidaDTO) {
        List<Produto> produtos = comidaDTO.getIngredientes()
                .stream()
                .map(idProduto -> produtoRepository.findById(idProduto))
                .toList();

       Comida comida = Comida.builder()
                .nome(comidaDTO.getNome())
                .peso(comidaDTO.getPeso())
                .preco(comidaDTO.getPreco())
                .hasPersonalizacao(comidaDTO.isHasPersonalizacao())
                .paraNpessoas(comidaDTO.getParaNpessoas())
                .calorias(comidaDTO.getCalorias())
                .build();

        List<ComidaProduto> listaComidaProduto =  produtos.stream()
                .map(produto -> ComidaProduto.builder()
                        .comida(comida)
                        .produto(produto)
                        .build())
                .toList();


        if(comidaDTO.isHasPersonalizacao()){
            List<Produto> listaProdutosPersonalizados = comidaDTO.getIngredientesPersonalizacao()
                    .stream()
                    .map(idProdutoP -> produtoRepository.findById(idProdutoP))
                    .toList();

            List<ComidaProdutoPersonalizacao> listaComidaProdutoPersonalizacao =  listaProdutosPersonalizados.stream()
                    .map(produto -> ComidaProdutoPersonalizacao.builder()
                            .comida(comida)
                            .produto(produto)
                            .build())
                    .toList();

            comida.setDescricaoPersonalizacao(comidaDTO.getDescricaoPersonalizacao());
            comida.setPrecoPersonalizacao(comidaDTO.getPrecoPersonalizacao());
            comida.setQuantidaDePersonalizacao(comidaDTO.getQuantidaDePersonalizacao());
            comida.setIngredientesPersonalizacao(listaComidaProdutoPersonalizacao);
        }
        comida.setIngredientes(listaComidaProduto);
        return comida;
    }
}
