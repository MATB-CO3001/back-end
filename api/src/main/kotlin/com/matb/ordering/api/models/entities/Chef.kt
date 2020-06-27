package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.Entity

@Entity
data class Chef (
    var vendorId: Int = 0
) : BaseEntity()