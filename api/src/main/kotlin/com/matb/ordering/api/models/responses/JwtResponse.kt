package com.matb.ordering.api.models.responses

class JwtResponse (
    var token: String = "",
    var type: String = "Bearer",
    var username: String = "",
    var email: String = ""
)