package com.ricky.adocao.repository

import com.ricky.adocao.models.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, String> {
}