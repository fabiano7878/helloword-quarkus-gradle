package br.com.comida.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComidaDTO {
    private long id;
    private String nome;
    private String peso;
    private List<Long> ingredientes;
    private Double preco;
    private String descricaoPersonalizacao;
    private Double precoPersonalizacao;
    private int quantidaDePersonalizacao;
    private int paraNpessoas;
    private Double calorias;
}
