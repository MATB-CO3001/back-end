package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendorRepository: JpaRepository<Vendor, Int>{
    fun findAllById(vendorId: Int) : Vendor
    fun findByName(vendorName: String) : Vendor
    fun existsByUsername(username: String): Boolean
}
