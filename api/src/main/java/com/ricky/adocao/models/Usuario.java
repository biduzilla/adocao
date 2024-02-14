package com.ricky.adocao.models;

import com.ricky.adocao.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN", length = 50)
    private String login;

    @Column(name = "SENHA", length = 50)
    private String senha;

    @Column(name = "ROLE", length = 10)
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Column(name = "TELEFONE", length = 10)
    private String telefone;

    @Column(name = "EMAIL", length = 50)
    private String email;
}
