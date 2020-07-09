package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class Food(
        @ManyToOne @JoinColumn(name = "vendor_id", referencedColumnName = "id")
        @JsonBackReference
        var vendor: Vendor? = null,
        var name: String = "",
        var price: Int = 0,
        var image: String = "",
        @JsonBackReference
        @OneToOne @JoinColumn(name = "food_id")
        var cartItem: CartItem? = null
) : BaseEntity()

