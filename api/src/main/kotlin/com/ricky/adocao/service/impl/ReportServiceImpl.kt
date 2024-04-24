package com.ricky.adocao.service.impl

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Report
import com.ricky.adocao.repository.ReportRepository
import com.ricky.adocao.service.ReportService
import com.ricky.adocao.utils.I18n
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class ReportServiceImpl(
    private val repository: ReportRepository,
    private val i18n: I18n
) : ReportService {
    override fun findAll(pageable: Pageable): Page<Report> {
        return repository.findAll(pageable)
    }

    override fun findById(reportId: String): Report {
        return repository.findById(reportId).orElseThrow { NotFoundException(i18n.getMessage("report.nao.encotrado")) }
    }

    override fun save(report: Report): Report {
        return repository.save(report)
    }

    override fun update(report: Report): Report {
        val reportRecuperado = findById(report.id)
        BeanUtils.copyProperties(report, reportRecuperado)
        return save(report)
    }

    override fun deleteById(reportId: String) {
        repository.deleteById(reportId)
    }

    override fun delete(report: Report) {
        repository.delete(report)
    }
}