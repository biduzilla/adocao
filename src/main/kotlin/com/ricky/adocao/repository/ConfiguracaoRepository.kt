package com.ricky.adocao.repository

import com.ricky.adocao.enums.ConfiguracaoEnum
import com.ricky.adocao.models.Configuracao
import org.springframework.data.jpa.repository.JpaRepository

interface ConfiguracaoRepository : JpaRepository<Configuracao, ConfiguracaoEnum> {
}