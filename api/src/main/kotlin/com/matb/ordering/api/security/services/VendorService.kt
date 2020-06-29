package com.matb.ordering.api.security.services

import com.matb.ordering.api.models.repositories.CustomerRepository
import com.matb.ordering.api.models.toCustomerDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VendorService (private val vendorRepository: CustomerRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val vendor = vendorRepository.findByUsername(username)
        if (!vendor.isPresent) {
            throw UsernameNotFoundException("User Not Found with username: " + username)
        }
        return vendor.get().toCustomerDetails()
    }
}