package com.matb.ordering.api.models.responses

import com.matb.ordering.api.models.entities.Role

class JwtResponse (
    var token: String = "",
    var type: String = "Bearer",
    var username: String = "",
    var email: String = ""
)