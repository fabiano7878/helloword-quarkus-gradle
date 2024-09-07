package br.com.comida.resource;


import br.com.comida.dto.ComidaDTO;
import br.com.comida.dto.ProdutoDTO;
import br.com.comida.service.ComidaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Objects;

@Path("/comida")
public class ComidaResource {

    @Inject
    private ComidaService comidaService;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ComidaDTO comidaDTO){
        return comidaService.validaComidaeCria((v1) -> Objects.nonNull(comidaDTO), comidaDTO);
    }
}
