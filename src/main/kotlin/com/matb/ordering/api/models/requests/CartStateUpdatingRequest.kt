package com.matb.ordering.api.models.requests

import com.matb.ordering.api.models.CartState

class CartStateUpdatingRequest (
    val cartId: Int,
    val newState: CartState
)