package com.matb.ordering.api.security.services

import com.matb.ordering.api.models.repositories.ChefRepository
import com.matb.ordering.api.models.repositories.CustomerRepository
import com.matb.ordering.api.models.toCustomerDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChefService (private val chefRepository: ChefRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val chef = chefRepository.findByUsername(username)
        if (!chef.isPresent) {
            throw UsernameNotFoundException("User Not Found with username: " + username)
        }
        return chef.get().toCustomerDetails()
    }
}