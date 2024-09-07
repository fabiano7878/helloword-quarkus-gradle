package br.com.comida.record;

import java.time.LocalDateTime;
import java.util.Objects;

public record ProdutoRecord(Long id, String nome, String peso, LocalDateTime dataDeValidade, String marca, Long quantidadeDisponivel){

    public static final Long ID_DEFAULT_REQUEST=0L;

  public ProdutoRecord {
      Objects.requireNonNull(nome);
      Objects.requireNonNull(peso);
      Objects.requireNonNull(dataDeValidade);
      Objects.requireNonNull(marca);
      if(quantidadeDisponivel <= 0){
          throw new NullPointerException("O valor da quantidade disponivel deve ser maior que o valor: "+quantidadeDisponivel+" para criar um produto.");
      }
    }

  /*  public ProdutoRecord(String nome, String peso, LocalDateTime dataDeValidade, String marca, Long quantidadeDisponivel){
      this(ID_DEFAULT_REQUEST, nome, peso, dataDeValidade, marca, quantidadeDisponivel);
    }*/
}