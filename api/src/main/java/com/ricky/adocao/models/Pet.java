package com.ricky.adocao.models;

import com.ricky.adocao.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    @Enumerated(EnumType.STRING)
    private PetIdadeEnum idade;

    @Column(name = "LOCALIZACAO")
    private PetCidadeEnum localizacao;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENERO")
    private PetGeneroEnum genero;

    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.DATE)
    private Date dataPublicacao;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private PetTipoEnum tipo;

    @Column(name = "TIPO_ANIMAL")
    @Enumerated(EnumType.STRING)
    private PetTipoAnimalEnum tipoAnimal;
}