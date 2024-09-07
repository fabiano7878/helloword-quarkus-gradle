package br.com.comida.repository;

import br.com.comida.entity.Comida;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComidaRepository implements PanacheRepositoryBase<Comida, Long> {
}
