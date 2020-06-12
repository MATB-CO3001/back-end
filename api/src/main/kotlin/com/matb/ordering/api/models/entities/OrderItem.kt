package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class OrderItem(
    @ManyToOne @JoinColumn(name = "food_id", nullable = false)
    var food: Food? = null,
    var quantity: Int = 0
) : BaseEntity()
