package br.com.comida.funcional;

import br.com.comida.entity.Produto;

import java.util.Objects;

@FunctionalInterface
public interface ValidateProdutoFuncional {
    boolean nameNullorBlank(String nome);

    default boolean isNull(Produto p){
        return Objects.isNull(p);
    }
}
