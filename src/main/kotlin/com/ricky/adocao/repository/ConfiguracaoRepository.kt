package com.ricky.adocao.repository

import com.ricky.adocao.enums.ConfiguracaoEnum
import com.ricky.adocao.models.Configuracao
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConfiguracaoRepository : JpaRepository<Configuracao, ConfiguracaoEnum> {
    fun findByCodConfiguracao(id: ConfiguracaoEnum): Optional<Configuracao>
}