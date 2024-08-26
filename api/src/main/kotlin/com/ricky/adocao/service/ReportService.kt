package com.ricky.adocao.service

import com.ricky.adocao.models.Report
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportService {
    fun findAll(pageable: Pageable): Page<Report>
    fun findById(reportId: String): Report
    fun save(report: Report):Report
    fun update(report: Report): Report
    fun deleteById(reportId: String)
    fun delete(report: Report)
}