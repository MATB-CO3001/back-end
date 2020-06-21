package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.CartState
import org.springframework.stereotype.Repository
import com.matb.ordering.api.models.entities.Cart
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface CartRepository : JpaRepository<Cart, Int>{
    fun findAllByState(cartState: CartState): List<Cart>
    fun findAllByVendorId(vendorId: Int): List<Cart>
}
