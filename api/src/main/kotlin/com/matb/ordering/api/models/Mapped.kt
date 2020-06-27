package com.matb.ordering.api.models

import com.matb.ordering.api.models.entities.Customer
import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Role
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.requests.SignupRequest
import com.matb.ordering.api.models.responses.JwtResponse
import com.matb.ordering.api.models.responses.VendorResponse
import com.matb.ordering.api.security.services.CustomerDetails

fun Vendor.toVendorResponse(foodList: List<Food>) = VendorResponse(
        id = id!!,
        name = name,
        state = state!!,
        foodList = foodList
)
fun Customer.toCustomerDetails() = CustomerDetails(
        username = username,
        password = password
)
fun Customer.toJwtResponse() = JwtResponse(
        token = token,
        username = username,
        email = email
)
fun SignupRequest.toCustomer(password: String) = Customer(
        username = username,
        password = password,
        email = email,
        role = Role.valueOf(role)
)