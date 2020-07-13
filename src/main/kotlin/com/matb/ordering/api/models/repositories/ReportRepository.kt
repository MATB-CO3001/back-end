package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Report
import com.matb.ordering.api.models.entities.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface ReportRepository: JpaRepository<Report, Int> {
    fun findByVendorAndDate(vendor:String, date:LocalDate): Optional<Report>
    fun existsByVendorAndDate(vendor: String, date: LocalDate): Boolean
    fun findAllByVendorAndDateBetween(vendor: String, startDate: LocalDate, endDate: LocalDate): List<Report>
}