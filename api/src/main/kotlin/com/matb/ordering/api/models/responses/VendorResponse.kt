package com.matb.ordering.api.models.responses

import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.Food

class VendorResponse (
    val id: Int,
    val name: String,
    val state: ActivityState,
    val foodList: List<Food>
)