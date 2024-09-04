package br.com.comida.repository;

import br.com.comida.entity.Comida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComidaRepository implements PanacheRepository<Comida> {
}
