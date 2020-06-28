package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity

@Entity
data class Vendor (
    var username: String = "",
    @JsonIgnore
    var password: String = "",
    var name: String = "",
    var state: ActivityState? = null,
    @JsonIgnore
    var role: String = "ROLE_VENDOR"
) : BaseUserEntity()
