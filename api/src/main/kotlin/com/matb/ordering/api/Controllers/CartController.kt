package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.repositories.CartItemRepository
import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.requests.CartCreationRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
@RequestMapping("/api/cart")
class CartController(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val foodRepository: FoodRepository
) {

    @PostMapping
    fun createCart(@RequestBody cartCreationRequest: CartCreationRequest): String {
        var cart = Cart(
                0,
                cartCreationRequest.customerId,
                cartCreationRequest.vendorId,
                CartState.PENDING
        )
        var temporarySavedCart = cartRepository.save(cart)
        for (cartItem in cartCreationRequest.orderedFoodList) {
            cartItem.cartId = temporarySavedCart.id!!
            temporarySavedCart.total += foodRepository.findById(cartItem.foodId).get().price * cartItem.quantity
            cartItemRepository.save(cartItem)
        }
        cartRepository.save(temporarySavedCart)
        return "Successfully order"
    }

}
