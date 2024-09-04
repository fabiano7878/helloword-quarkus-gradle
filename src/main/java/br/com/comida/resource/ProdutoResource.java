package br.com.comida.resource;


import br.com.comida.dto.ProdutoDTO;
import br.com.comida.service.ComidaService;
import br.com.comida.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produto")
public class ProdutoResource {

    @Inject
    private ProdutoService produtoService;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProdutoDTO produtoDTO){
        return produtoService.createProduto(produtoDTO);
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
        return produtoService.update(id, produtoDTO);
    }

}
