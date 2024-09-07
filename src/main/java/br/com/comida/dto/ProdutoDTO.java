package br.com.comida.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "Erro: informar o Nome do produto é obrigatório!")
    @Size(max = 70, message = "Erro: o Nome deve ter no máximo 70 caracteres.")
    private String nome;

    @NotBlank(message = "Erro: informar o Peso do produto é obrigatório!")
    @Size(max = 10, message = "Erro: o Peso deve ter no máximo 10 caracteres.")
    private String peso;

    @NotNull(message = "informar a Data de validade do produto é obrigatória!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss a")
    private LocalDateTime dataDeValidade;

    @NotBlank(message = "Erro: informar a Marca do produto é obrigatório!")
    @Size(max = 70, message = "Erro: o Marca do produto deve ter no máximo 70 caracteres.")
    private String marca;

    @NotNull(message = "informar a quantidadeDisponivel deste produto é obrigatória!")
    private long quantidadeDisponivel;
}
