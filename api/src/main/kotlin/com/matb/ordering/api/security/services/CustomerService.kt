package com.matb.ordering.api.security.services

import com.matb.ordering.api.models.entities.Customer
import com.matb.ordering.api.models.repositories.CustomerRepository
import com.matb.ordering.api.models.toCustomerDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random.Default.Companion

@Service
class CustomerService (private val customerRepository: CustomerRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val customer = customerRepository.findByUsername(username)
        if (!customer.isPresent) {
            throw UsernameNotFoundException("User Not Found with username: " + username)
        }
        return customer.get().toCustomerDetails()
    }
}