package com.matb.ordering.api.models.requests.creation

import com.matb.ordering.api.models.FoodState

class FoodRequest (
        var name: String,
        var price: Int,
        var image: String,
        var state: FoodState = FoodState.AVAILABLE
)