package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.OrderState
import com.matb.ordering.api.models.entities.Orders
import com.matb.ordering.api.models.repositories.OrdersRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrdersController(private val orderRepository: OrdersRepository) {
    @GetMapping
    fun hello():String = "Hello, world!"

    @GetMapping("/orders")
    fun getAllOrder(): List<Orders> = orderRepository.findAll()

    @GetMapping("/orders/pending")
    fun getAllPendingOrder(): List<Orders> = orderRepository.findAllByOrderState(OrderState.PENDING)

    @PostMapping("/orders")
    fun createOrder(@RequestBody orders: Orders) = orderRepository.save(orders)
}
