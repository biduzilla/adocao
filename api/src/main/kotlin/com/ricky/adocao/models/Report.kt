package com.ricky.adocao.models

import jakarta.persistence.*
import lombok.Data

@Entity
@Table(name = "REPORT")
@Data
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="REPORT_ID")
    var id: String = "",

    @Column(name = "PET_ID")
    var petId: String = "",

    @Column(name = "USER_REPORT_ID")
    var userReportId: String = "",
)
