package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Food (
    var name: String = "",
    var price: Int = 0,
    var image: String = "",
    @ManyToOne @JoinColumn(name = "vendor_id", nullable = false)
    var vendor: Vendor? = null
) : BaseEntity()

