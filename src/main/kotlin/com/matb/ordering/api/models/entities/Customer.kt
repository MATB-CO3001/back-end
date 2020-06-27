package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.entities.base.BaseEntity
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity

@Entity
data class Customer (
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var token: String = "",
        var role: Role? = null
) : BaseEntity()