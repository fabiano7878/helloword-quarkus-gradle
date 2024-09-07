package br.com.comida.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComidaDTO {
    private long id;

    @NotBlank(message = "Erro: o Nome é obrigatório")
    @Size(max = 70, message = "Erro: o Nome deve ter no máximo 70 caracteres.")
    private String nome;

    @NotBlank(message = "Erro: Peso é obrigatório")
    @Size(max = 10, message = "Erro: o Peso deve ter no máximo 10 caracteres.")
    private String peso;

    @NotEmpty.List({})
    private List<Long> ingredientes;

    @NotNull(message = "Erro: o Preço precisa estar definido")
    private Double preco;

    private boolean hasPersonalizacao;

    private String descricaoPersonalizacao;

    private List<Long> ingredientesPersonalizacao;

    private Double precoPersonalizacao;

    private long quantidaDePersonalizacao;

    @NotNull(message = "Erro: o paraNpessoas precisa estar definido")
    private long paraNpessoas;

    private Double calorias;
}
