package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.Entity

@Entity
class Customer (
    var userName: String = ""
) : BaseEntity()