package com.matb.ordering.api.controllers

import com.matb.ordering.api.exceptions.UserNotFoundException
import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.entities.CartItem
import com.matb.ordering.api.models.repositories.*
import com.matb.ordering.api.models.requests.creation.CartRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import java.lang.Math.abs
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@RestController
@RequestMapping("/api/cart")
class CartController(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val foodRepository: FoodRepository,
    private val vendorRepository: VendorRepository,
    private val customerRepository: CustomerRepository,
    private val template: SimpMessagingTemplate
) {

    @PostMapping
    fun createCart(@RequestBody cartRequest: CartRequest): ResponseEntity<List<Cart>> {
        var carts = mutableListOf<Cart>()
        for (item in cartRequest.orderedFoodList) {
            var vendorItem = foodRepository.findById(item.foodId).get().vendor!!.username
            if (carts.isEmpty() || carts.last().vendor != vendorItem) {
                carts.add(cartRepository.save(Cart(
                        0,
                        cartRequest.customer,
                        vendorItem,
                        CartState.PENDING,
                        LocalDateTime.now(ZoneId.of("GMT+07:00"))
                )))
            }
            var food = foodRepository.findById(item.foodId).get()
            carts.last().total += food.price * item.quantity
            cartItemRepository.save(CartItem(food,carts.last(),item.quantity))
        }
        template.convertAndSend("/chef-message", "")
        return ResponseEntity(carts,HttpStatus.CREATED)
    }

    @GetMapping("/{username}")
    fun getAllCartDone(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        if (!vendorRepository.existsByUsername(username)) {
            throw UserNotFoundException("vendor", username)
        }
        var stateList = setOf(CartState.DONE)
        return ResponseEntity(cartRepository.findAllByVendorAndStateIn(username, stateList), HttpStatus.OK)
    }
    @GetMapping("/customer/{username}")
    fun getAllCarByCustomer(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        if (!customerRepository.existsByUsername(username)) {
            throw UserNotFoundException("customer", username)
        }
        return ResponseEntity(cartRepository.findAllByCustomer(username), HttpStatus.OK)
    }

    @PostMapping("/cheat")
    fun cheatCart(@RequestBody cartRequest: CartRequest): ResponseEntity<List<Cart>> {
        var carts = mutableListOf<Cart>()
        var cheatingTime = LocalDateTime.now(ZoneId.of("GMT+07:00")).plusDays(-abs(Random().nextLong()%30))
        for (item in cartRequest.orderedFoodList) {
            var vendorItem = foodRepository.findById(item.foodId).get().vendor!!.username
            if (carts.isEmpty() || carts.last().vendor != vendorItem) {
                carts.add(cartRepository.save(Cart(
                        0,
                        cartRequest.customer,
                        vendorItem,
                        CartState.PENDING,
                        cheatingTime
                )))
            }
            var food = foodRepository.findById(item.foodId).get()
            carts.last().total += food.price * item.quantity
            cartItemRepository.save(CartItem(food,carts.last(),item.quantity))
        }
        template.convertAndSend("/chef-message", "")
        return ResponseEntity(carts,HttpStatus.CREATED)
    }

}
