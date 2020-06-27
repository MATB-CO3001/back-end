package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.repositories.CustomerRepository
import com.matb.ordering.api.models.requests.LoginRequest
import com.matb.ordering.api.models.requests.SignupRequest
import com.matb.ordering.api.models.toCustomer
import com.matb.ordering.api.models.toJwtResponse
import com.matb.ordering.api.security.jwt.JwtUtils
import com.matb.ordering.api.security.services.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")

class CustomerController (
        private val customerRepository: CustomerRepository
) {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var jwtUtils: JwtUtils
    @Autowired
    lateinit var encoder:PasswordEncoder

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    fun hello() = "okok matb"

    @PostMapping("/signup")
    fun regiterCustomer(@RequestBody signupRequest: SignupRequest) : ResponseEntity<Any> {
        if (customerRepository.existsByUsername(signupRequest.username)){
            return ResponseEntity(Error("Username is already taken!"), HttpStatus.OK)
        }
        if (customerRepository.existsByUsername(signupRequest.email)){
            return ResponseEntity(Error("Email is already taken!"), HttpStatus.OK)
        }

        return ResponseEntity(customerRepository.save(signupRequest.toCustomer(encoder.encode(signupRequest.password))), HttpStatus.OK)
    }

    @PostMapping("/signin")
    fun authenticateCustomer(@RequestBody loginRequest: LoginRequest) : ResponseEntity<Any> {
        var optionCustomer = customerRepository.findByUsername(loginRequest.username)
        if (!optionCustomer.isPresent) {
            return ResponseEntity("Username does not exist", HttpStatus.FORBIDDEN)
        }
        var customer = optionCustomer.get()
        if (!encoder.matches(loginRequest.password, customer.password)) {
            return ResponseEntity("Password wrong", HttpStatus.FORBIDDEN)
        }
        if (customer.token == "") {
            customer.token = jwtUtils.generateJwtToken(customer)
            customerRepository.save(customer)
        }
        return ResponseEntity(customer.toJwtResponse(),HttpStatus.OK)
    }
}