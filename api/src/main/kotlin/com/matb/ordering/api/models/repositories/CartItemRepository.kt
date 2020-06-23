package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.entities.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository: JpaRepository<CartItem, Int> {

}