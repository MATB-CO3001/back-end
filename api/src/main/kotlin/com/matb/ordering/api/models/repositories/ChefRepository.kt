package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.Chef
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChefRepository: JpaRepository<Chef, Int>{
}