package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
data class Vendor (
    var name: String = "",
    var email: String = "",
    var state: ActivityState? = null,
    @OneToMany(mappedBy = "vendor", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var food: Set<Food> = HashSet()
) : BaseEntity()
