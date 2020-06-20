package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.repositories.CartRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrdersController(private val orderRepository: CartRepository) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody cart: Cart): ResponseEntity<Cart>{
        return ResponseEntity(orderRepository.save(cart), HttpStatus.OK)
    }

    @GetMapping("/orders")
    fun getAllOrder(): List<Cart> = orderRepository.findAll()

    @GetMapping("/orders/pending")
    fun getAllPendingOrder(): List<Cart> = orderRepository.findAllByCartState(CartState.PENDING)

}
