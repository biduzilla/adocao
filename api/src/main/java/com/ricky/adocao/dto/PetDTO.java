package com.ricky.adocao.dto;

import com.ricky.adocao.enums.PetGeneroEnum;
import com.ricky.adocao.enums.PetIdadeEnum;
import com.ricky.adocao.enums.PetTipoAnimalEnum;
import com.ricky.adocao.enums.PetTipoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetDTO {
    private Long id;
    private String nome;
    private PetIdadeEnum idade;
    private String localizacao;
    private UsuarioDTO usuario;
    private String descricao;
    private PetGeneroEnum genero;
    private LocalDate dataPublicacao;
    private byte[] foto;
    private PetTipoEnum tipo;
    private PetTipoAnimalEnum tipoAnimal;
}
