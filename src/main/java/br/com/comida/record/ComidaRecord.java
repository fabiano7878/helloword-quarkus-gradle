package br.com.comida.record;

import java.util.List;

public record ComidaRecord(long id, String nome, Double calorias, List<ProdutoRecord> produtos) {
}
