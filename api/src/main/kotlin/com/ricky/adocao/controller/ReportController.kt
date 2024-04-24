package com.ricky.adocao.controller

import com.ricky.adocao.models.Report
import com.ricky.adocao.service.ReportService
import com.ricky.adocao.utils.CacheConstants
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/report")
class ReportController(private val reportService: ReportService) {

    @GetMapping("/find-all")
    @Cacheable(CacheConstants.USUARIOS_CACHE)
    fun findAll(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @PageableDefault(
            size = 10,
        ) paginacao: Pageable
    ): Page<Report> {
        val pageable = PageRequest.of(page, paginacao.pageSize)
        return reportService.findAll(pageable)
    }

    @GetMapping("/get-report/{reportId}")
    fun findById(@PathVariable reportId: String): Report {
        return reportService.findById(reportId)
    }

    @PostMapping("/save")
    @Transactional
    @CacheEvict(value = [CacheConstants.REPORT_CACHE], allEntries = true)
    fun insert(@RequestBody report: Report): ResponseEntity<Report> {
        val reportSave = reportService.save(report)
        return ResponseEntity.status(HttpStatus.CREATED).body(reportSave)
    }

    @PutMapping("/update")
    @Transactional
    @CacheEvict(value = [CacheConstants.REPORT_CACHE], allEntries = true)
    fun update(@RequestBody report: Report): ResponseEntity<Report> {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.update(report))
    }

    @DeleteMapping("/delete/{reportId}")
    @Transactional
    @CacheEvict(value = [CacheConstants.REPORT_CACHE], allEntries = true)
    fun deleteById(@PathVariable reportId: String) {
        reportService.deleteById(reportId)
    }
}