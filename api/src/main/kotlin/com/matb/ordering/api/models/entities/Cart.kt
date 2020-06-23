package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class Cart(
    var total: Int = 0,
    var customerId: Int = 0,
    var vendorId: Int = 0,
    var state: CartState? = null
) : BaseEntity()
