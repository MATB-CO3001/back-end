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
    fun createCart(@RequestBody cartRequest: CartRequest): ResponseEntity<Cart> {
        var cart = Cart(
                0,
                cartRequest.customer,
                cartRequest.vendor,
                CartState.PENDING,
                LocalDateTime.now(ZoneId.of("GMT+07:00"))
        )
        var temporarySavedCart = cartRepository.save(cart)
        for (item in cartRequest.orderedFoodList) {
            var food = foodRepository.findById(item.foodId).get()
            temporarySavedCart.total += food.price * item.quantity
            cartItemRepository.save(CartItem(food,cart,item.quantity))
        }
        template.convertAndSend("/chef-message", "")
        return ResponseEntity(cartRepository.save(temporarySavedCart),HttpStatus.CREATED)
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
    fun cheatCart(@RequestBody cartRequest: CartRequest): ResponseEntity<Cart> {
        var cart = Cart(
                0,
                cartRequest.customer,
                cartRequest.vendor,
                CartState.PENDING,
                LocalDateTime.now(ZoneId.of("GMT+07:00")).plusDays(-abs(Random().nextLong()%30))
        )
        var temporarySavedCart = cartRepository.save(cart)
        for (item in cartRequest.orderedFoodList) {
            var food = foodRepository.findById(item.foodId).get()
            temporarySavedCart.total += food.price * item.quantity
            cartItemRepository.save(CartItem(food,cart,item.quantity))
        }
        template.convertAndSend("/chef-message", "")
        return ResponseEntity(cartRepository.save(temporarySavedCart),HttpStatus.CREATED)
    }

}
