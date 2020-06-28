package com.matb.ordering.api.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.matb.ordering.api.models.entities.base.BaseEntity
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity

@Entity
data class Chef (
    var username: String = "",
    @JsonIgnore
    var password: String = "",
    var vendorId: Int = 0,
    @JsonIgnore
    var role: String = "ROLE_CHEF"
) : BaseUserEntity()