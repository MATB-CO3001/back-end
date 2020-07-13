package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface ReportRepository: JpaRepository<Report, Int> {
    fun findByDate(date:LocalDate): Optional<Report>
    fun existsByDate(date: LocalDate): Boolean

    fun findAllByDateBetween(startDate: LocalDate, endDate: LocalDate): List<Report>
}