package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.entities.CartItem
import com.matb.ordering.api.models.repositories.CartItemRepository
import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.requests.creation.CartRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneId

@RestController
@RequestMapping("/api/cart")
class CartController(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val foodRepository: FoodRepository
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
        return ResponseEntity(cartRepository.save(temporarySavedCart),HttpStatus.CREATED)
    }

    @GetMapping("/{username}")
    fun getAllCartDone(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        var stateList = setOf(CartState.DONE)
        return ResponseEntity(cartRepository.findAllByVendorAndStateIn(username, stateList), HttpStatus.OK)
    }
    @GetMapping("/customer/{username}")
    fun getAllCarByCustomer(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByCustomer(username), HttpStatus.OK)
    }



}
