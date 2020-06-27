package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.base.BaseUserEntity
import javax.persistence.Entity

@Entity
data class Vendor (
    var name: String = "",
    var state: ActivityState? = null
) : BaseUserEntity()
