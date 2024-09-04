package br.com.comida.service;

import br.com.comida.dto.ComidaDTO;
import br.com.comida.dto.ProdutoDTO;
import br.com.comida.funcional.ValidateProdutoFuncional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@ApplicationScoped
public class ComidaService {

    public Response createComida(ComidaDTO comidaDTO) {
        return Response.ok(ComidaDTO.builder().build()).status(200).build();
    }
}
