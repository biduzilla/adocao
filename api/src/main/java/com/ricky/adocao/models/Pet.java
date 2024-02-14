package com.ricky.adocao.models;

import com.ricky.adocao.enums.PetGeneroEnum;
import com.ricky.adocao.enums.PetIdadeEnum;
import com.ricky.adocao.enums.PetTipoAnimalEnum;
import com.ricky.adocao.enums.PetTipoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    private String id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    @Enumerated(EnumType.STRING)
    private PetIdadeEnum idade;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENERO")
    private PetGeneroEnum genero;

    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.TIME)
    private LocalDate dataPublicacao;

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