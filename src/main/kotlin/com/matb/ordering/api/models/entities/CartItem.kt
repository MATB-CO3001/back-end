package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class CartItem(
    @JsonManagedReference
    @OneToOne
    var food: Food? = null,
    @JsonBackReference
    @ManyToOne @JoinColumn(name = "cart_id")
    var cart: Cart? = null,
    var quantity: Int = 0
) : BaseEntity()
