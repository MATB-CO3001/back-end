package com.matb.ordering.api.models.responses

import org.springframework.http.HttpStatus

class LoginResponse (
        var status: HttpStatus,
        var message: String
)