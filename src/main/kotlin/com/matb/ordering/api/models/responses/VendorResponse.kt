package com.matb.ordering.api.models.responses

import com.matb.ordering.api.models.ActivityState

class VendorResponse (
    val id: Int,
    val name: String,
    val state: ActivityState
)