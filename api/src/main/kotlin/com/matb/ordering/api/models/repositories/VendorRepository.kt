package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VendorRepository: JpaRepository<Vendor, Int>{
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Optional<Vendor>
    fun deleteByUsername(username: String)
}
