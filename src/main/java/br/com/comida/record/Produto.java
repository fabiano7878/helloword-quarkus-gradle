package br.com.comida.record;

import java.time.LocalDateTime;

public record  Produto (long id, String nome, LocalDateTime dtValidade){
}