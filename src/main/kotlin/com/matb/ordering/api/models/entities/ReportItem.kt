package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class ReportItem (
        @JsonManagedReference
        @OneToOne @JoinColumn(name = "food_id")
        var food: Food? = null,
        @JsonBackReference
        @ManyToOne @JoinColumn(name = "report_id")
        var report: Report? = null,
        var quantity: Int = 0
) : BaseEntity()