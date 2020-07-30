package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.FoodState
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
        var state: FoodState? = null,
        @JsonBackReference
        @OneToOne
        var cartItem: CartItem? = null,
        @JsonBackReference
        @OneToOne
        var reportItem: ReportItem? = null
) : BaseEntity()

