package com.matb.ordering.api.models.requests.creation

import com.matb.ordering.api.models.ActivityState

class VendorRequest (
    var username: String,
    var password: String,
    var name: String,
    var state: ActivityState = ActivityState.ACTIVE
)