package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.base.BaseEntity
import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.*

@Entity
data class Cart(
    var total: Int = 0,
    var customer: String = "",
    var vendor: String = "",
    var state: CartState? = null,
    @JsonFormat(pattern="HH:mm:ss dd-MM-yyyy")
    var createAt: LocalDateTime? = null,
    @JsonManagedReference
    @OneToMany(mappedBy = "cart")
    var cartItem: List<CartItem>? = null
) : BaseEntity()
