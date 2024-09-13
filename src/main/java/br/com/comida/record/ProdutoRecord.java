package br.com.comida.record;

import java.time.LocalDateTime;
import java.util.Objects;

public record ProdutoRecord(Long id, String nome, String peso, LocalDateTime dataDeValidade, String marca, Long quantidadeDisponivel){
  public ProdutoRecord {
      Objects.requireNonNull(nome);
      Objects.requireNonNull(peso);
      Objects.requireNonNull(dataDeValidade);
      Objects.requireNonNull(marca);
      Objects.requireNonNull(quantidadeDisponivel);
    }
}