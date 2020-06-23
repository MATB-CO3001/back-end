package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.requests.CartStateUpdatingRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ChefController (
        private val cartRepository: CartRepository
){
    @GetMapping("/vendor/{id}/chef")
    fun getAllPendingCart(){

    }

    @PostMapping("/vendor/{id}/chef")
    fun updateCartState(@RequestBody cartStateUpdatingRequest: CartStateUpdatingRequest){

    }
}