package com.ricky.adocao.dto;

import com.ricky.adocao.enums.RolesEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String login;
    private RolesEnum role;
    private String telefone;
    private String email;
}
