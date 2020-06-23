package com.matb.ordering.api.models

import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.responses.VendorResponse

fun Vendor.toVendorResponse(foodList: List<Food>) = VendorResponse(
        id = id!!,
        name = name,
        state = state!!,
        foodList = foodList
)