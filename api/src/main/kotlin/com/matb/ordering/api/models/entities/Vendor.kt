package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Vendor (
        var name: String = "",
        var username: String = "",
        @JsonIgnore
        var password: String = "",
        var state: ActivityState? = null,
        @JsonManagedReference
        @OneToMany(mappedBy = "vendor", orphanRemoval = true)
        var foodList: List<Food>? = null,
        @JsonIgnore
        var role: String = ""
) : BaseUserEntity()
