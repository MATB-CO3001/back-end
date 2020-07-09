package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository: JpaRepository<Food, Int>{
    fun deleteAllByVendor(vendor: String)
}

