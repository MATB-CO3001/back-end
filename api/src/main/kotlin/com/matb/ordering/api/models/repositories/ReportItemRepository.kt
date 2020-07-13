package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Report
import com.matb.ordering.api.models.entities.ReportItem
import com.matb.ordering.api.models.entities.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReportItemRepository: JpaRepository<ReportItem, Int> {
    fun existsByReportAndFood(report: Report, food: Food): Boolean
    fun findByReportAndFood(report: Report, food: Food): Optional<ReportItem>
}