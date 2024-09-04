package br.com.comida.entity;


import br.com.comida.record.Produto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="comida")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Double calorias;

    @Column
    private Integer idIngredientes;

    @Column
    private String peso;

    @Column
    private String ingredientes;

    @Column
    private Double preco;

    @Column
    private String descricaoPersonalizacao;

    @Column
    private Double precoPersonalizacao;

    @Column
    private int quantidaDePersonalizacao;

    @Column
    private int paraNpessoas;


}
