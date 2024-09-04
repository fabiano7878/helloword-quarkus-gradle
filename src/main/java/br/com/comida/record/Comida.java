package br.com.comida.record;

import java.util.List;

public record Comida(long id, String nome, Double calorias, List<Produto> produtos) {
}
