package com.matb.ordering.api.models.requests

class SignupRequest (
        var username: String  = "" ,
        var password: String = "",
        var email: String = "",
        var role: String = ""
)