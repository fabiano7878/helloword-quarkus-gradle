package br.com.comida.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name="produto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String peso;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeValidade;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Long quantidadeDisponivel;
}
