package com.matb.ordering.api.models.entities.base

import com.fasterxml.jackson.annotation.JsonIgnore
import com.matb.ordering.api.models.RoleState
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseUserEntity: BaseEntity() {
    var token: String = ""
}