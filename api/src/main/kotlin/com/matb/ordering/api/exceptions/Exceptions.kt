package com.matb.ordering.api.exceptions

class UserNotFoundException(var type: String, var username: String) : RuntimeException()

class UserAlreadyTakenException(var type: String, var username: String) : RuntimeException()

class FoodNotFoundException(var food: Int) : RuntimeException()
