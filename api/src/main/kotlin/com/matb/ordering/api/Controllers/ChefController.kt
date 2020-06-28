package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.requests.CartStateUpdatingRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chef")
class ChefController (
        private val cartRepository: CartRepository
){
    @GetMapping("/{vendorId}")
    fun getAllPendingCart(@PathVariable (value = "vendorId") vendorId: Int): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByVendorIdAndState(vendorId, CartState.PENDING), HttpStatus.OK)
    }

    @PostMapping
    fun updateCartState(@RequestBody cartStateUpdatingRequest: CartStateUpdatingRequest): Cart {
        val newUpdatedStateCart = cartRepository.findById(cartStateUpdatingRequest.cartId).get()
        newUpdatedStateCart.state = cartStateUpdatingRequest.newState
        cartRepository.save(newUpdatedStateCart)
        return newUpdatedStateCart
    }
}