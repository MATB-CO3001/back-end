package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseEntity
import org.hibernate.criterion.Order
import javax.persistence.*

@Entity
data class Vendor (
    var name: String = "",
    var state: ActivityState? = null,
    @JsonManagedReference
    @OneToMany(mappedBy = "vendor")
    var food: List<Food>? = null
) : BaseEntity()
