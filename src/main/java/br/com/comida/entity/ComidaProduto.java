package br.com.comida.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="comida_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComidaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comida", nullable = false)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;
}
