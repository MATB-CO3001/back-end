package com.matb.ordering.api.models.entities.base

import com.matb.ordering.api.models.RoleState
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseUserEntity: BaseEntity() {
    var username: String = ""
    var password: String = ""
    var email: String = ""
    var role: RoleState? = null
}