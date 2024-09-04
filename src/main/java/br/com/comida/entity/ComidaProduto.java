package br.com.comida.entity;

import jakarta.persistence.*;

@Entity
@Table(name="comidaproduto")
public class ComidaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idComida;

    @Column
    private Long idProduto;
}
