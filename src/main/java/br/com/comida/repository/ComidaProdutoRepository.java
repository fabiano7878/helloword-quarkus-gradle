package br.com.comida.repository;

import br.com.comida.entity.ComidaProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComidaProdutoRepository implements PanacheRepositoryBase<ComidaProduto, Long> {
}
