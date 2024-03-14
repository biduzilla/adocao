package com.ricky.adocao.service

import com.ricky.adocao.enums.ConfiguracaoEnum
import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Configuracao
import com.ricky.adocao.repository.ConfiguracaoRepository
import com.ricky.adocao.utils.I18n
import org.springframework.stereotype.Service

@Service
class ConfiguracaoService(
    private val repository: ConfiguracaoRepository,
    private val i18n: I18n
) {
    fun findById(codConfiguracao: ConfiguracaoEnum): Configuracao {
        return repository.findById(codConfiguracao)
            .orElseThrow { NotFoundException(i18n.getMessage("configuracao.nao.encontrado")) }
    }

    fun findAll(): List<Configuracao> {
        return repository.findAll()
    }
}