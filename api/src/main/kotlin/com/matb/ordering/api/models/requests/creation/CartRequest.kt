package com.matb.ordering.api.models.requests.creation

import com.matb.ordering.api.models.entities.CartItem
import com.matb.ordering.api.models.requests.CartItemRequest
import javax.persistence.Id

class CartRequest(
        val orderedFoodList: List<CartItemRequest>,
        val customer: String,
        val vendor: String
)