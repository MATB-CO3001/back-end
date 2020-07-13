package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Report (
        var date: LocalDate? = null,
        var total: Int = 0,
        @JsonManagedReference
        @OneToMany(mappedBy = "report", orphanRemoval = true)
        var reportList: List<ReportItem>? = null,
        var vendor: String = ""
) : BaseEntity()