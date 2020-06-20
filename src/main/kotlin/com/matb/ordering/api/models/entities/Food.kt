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
    @JsonIgnore
    var vendorId: Int = 0
) : BaseEntity()

