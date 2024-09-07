package br.com.comida.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="comida_produto_personalizacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComidaProdutoPersonalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comida", nullable = true)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = true)
    private Produto produto;
}
