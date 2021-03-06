package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findByUsername(username: String): Optional<Customer>
    fun existsByUsername(username: String): Boolean
}