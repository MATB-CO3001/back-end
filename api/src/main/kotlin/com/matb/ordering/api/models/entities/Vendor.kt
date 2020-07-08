package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Vendor (
    var username: String = "",
    @JsonIgnore
    var password: String = "",
    var name: String = "",
    var state: ActivityState? = null,
    @JsonManagedReference
    @OneToMany
    var food: List<Food>? = null,
    @JsonIgnore
    var role: String = "ROLE_VENDOR"

) : BaseUserEntity()
