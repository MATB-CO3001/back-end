package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class CartItem(
    var foodId: Int = 0,
    var quantity: Int = 0,
    var cartId: Int = 0
) : BaseEntity()
