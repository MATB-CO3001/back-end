package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Int> {
    fun findByUsername(username: String): Customer
}