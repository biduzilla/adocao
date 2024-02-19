package com.ricky.adocao.dto;

import com.ricky.adocao.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class PetDTO {
    private Long id;
    private String nome;
    private PetIdadeEnum idade;
    private PetCidadeEnum localizacao;
    private UsuarioDTO usuario;
    private String descricao;
    private PetGeneroEnum genero;
    private Date dataPublicacao;
    private byte[] foto;
    private PetTipoEnum tipo;
    private PetTipoAnimalEnum tipoAnimal;
}
