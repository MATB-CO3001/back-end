package com.matb.ordering.api.models.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseUserEntityRepository<T>:JpaRepository<T, Int> {
    fun findByUsername(username: String): T

}
