package com.ricky.adocao.models

import com.ricky.adocao.enums.ConfiguracaoEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name="CONFIGURACAO")
data class Configuracao(

    @Id
    @Column(name = "CODCONFIGURACAO", columnDefinition = "int")
    val codConfiguracao: ConfiguracaoEnum,

    @Column(name = "CONFIGURACAO")
    val configuracao: String
)
