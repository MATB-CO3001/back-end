package com.matb.ordering.api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {
    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handleUserNotFoundException(userNotFoundException: UserNotFoundException) : ResponseEntity<Any> {
        return ResponseEntity("${userNotFoundException.type} not found with username: ${userNotFoundException.username}", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [UserAlreadyTakenException::class])
    fun handleUserNotFoundException(userAlreadyTakenException: UserAlreadyTakenException) : ResponseEntity<Any> {
        return ResponseEntity("${userAlreadyTakenException.type} is already taken with id: ${userAlreadyTakenException.username}", HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [FoodNotFoundException::class])
    fun handleFoodNotFoundException(foodNotFoundException: FoodNotFoundException) : ResponseEntity<Any> {
        return ResponseEntity("Food not found with name: ${foodNotFoundException.food}", HttpStatus.NOT_FOUND)
    }
}