package com.matb.ordering.api.security.services

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetails(
        username: String,
        password: String,
        var role: String
): UserDetails {
    override fun getAuthorities(): Collection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(role)
    }

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}