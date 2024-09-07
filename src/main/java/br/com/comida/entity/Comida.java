package br.com.comida.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="comida")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String peso;

    @OneToMany(mappedBy = "comida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComidaProduto> ingredientes;

    @Column(nullable = false)
    private Double preco;

    @Column
    private boolean hasPersonalizacao;

    @Column
    private String descricaoPersonalizacao;

    @Column
    @OneToMany(mappedBy = "comida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComidaProdutoPersonalizacao> ingredientesPersonalizacao;

    @Column
    private Double precoPersonalizacao;

    @Column
    private Long quantidaDePersonalizacao;

    @Column
    private Long paraNpessoas;

    @Column
    private Double calorias;
}
