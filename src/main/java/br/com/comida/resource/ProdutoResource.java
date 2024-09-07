package br.com.comida.resource;


import br.com.comida.dto.ProdutoDTO;
import br.com.comida.record.ProdutoRecord;
import br.com.comida.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Objects;

@Path("/produto")
public class ProdutoResource {

    @Inject
    private ProdutoService produtoService;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProdutoRecord produto){
        return produtoService.validaProdutoAndCria((v1) -> Objects.isNull(produto), produto);
    }

    @POST
    @Transactional
    @Path("/dto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWithDTO(ProdutoDTO produtoDTO){
        return produtoService.validaProdutoAndCriaWithDTO((v1) -> Objects.isNull(produtoDTO), produtoDTO);
    }

    @GET
    @Transactional
    public Response getAll(){
        return produtoService.getAll();
    }

    @Path("{id}")
    @PATCH
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ProdutoDTO produtoDTO){
        produtoDTO.setId(id);
        return produtoService.validateAndUpdate((v1) -> Objects.nonNull(v1), produtoDTO);
    }

    @Path("{id}")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        return produtoService.delete(id);
    }
}
