package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class Food(
    var name: String = "",
    var price: Int = 0,
    var image: String = "",
    @JsonBackReference
    @OneToOne(mappedBy = "food")
    var cartItem: CartItem? = null,
    @JsonBackReference
    @ManyToOne @JoinColumn(name = "vendor_id")
    var vendor: Vendor? = null
) : BaseEntity()

