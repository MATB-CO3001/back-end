package com.matb.ordering.api.models

import com.matb.ordering.api.models.entities.Chef
import com.matb.ordering.api.models.entities.Customer
import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.requests.SignupRequest
import com.matb.ordering.api.models.responses.JwtResponse
import com.matb.ordering.api.models.responses.VendorResponse
import com.matb.ordering.api.security.services.UserDetails

fun Vendor.toVendorResponse(foodList: List<Food>) = VendorResponse(
        id = id!!,
        state = state!!,
        name = name,
        foodList = foodList
)
fun Customer.toCustomerDetails() = UserDetails(
        username = username,
        password = password,
        role = role
)
fun Vendor.toCustomerDetails() = UserDetails(
        username = username,
        password = password,
        role = role
)
fun Chef.toCustomerDetails() = UserDetails(
        username = username,
        password = password,
        role = role
)
fun Customer.toJwtResponse() = JwtResponse(
        token = token,
        username = username
)
fun SignupRequest.toCustomer(password: String) = Customer(
        username = username,
        password = password
)