package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.matb.ordering.api.models.entities.base.BaseEntity
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity

@Entity
data class Customer (
        var username: String = "",
        @JsonIgnore
        var password: String = "",
        @JsonIgnore
        var role: String = "ROLE_USER"
) : BaseUserEntity()