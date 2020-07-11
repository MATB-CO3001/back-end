package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.repositories.ChefRepository
import com.matb.ordering.api.models.requests.CartStateUpdatingRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chef")
class ChefController (
        private val cartRepository: CartRepository,
        private val chefRepository: ChefRepository
){
    @GetMapping("/{username}/pending")
    fun getAllPendingCart(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByVendorAndState(username, CartState.PENDING), HttpStatus.OK)
    }

    @GetMapping("/{username}/inprogress")
    fun getAllInprogressCart(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByVendorAndState(username, CartState.INPROGRESS), HttpStatus.OK)
    }

    @PostMapping
    fun updateCartState(@RequestBody cartStateUpdatingRequest: CartStateUpdatingRequest): Cart {
        val newUpdatedStateCart = cartRepository.findById(cartStateUpdatingRequest.cartId).get()
        newUpdatedStateCart.state = cartStateUpdatingRequest.newState
        cartRepository.save(newUpdatedStateCart)
        return newUpdatedStateCart
    }
}