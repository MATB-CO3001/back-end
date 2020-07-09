package com.matb.ordering.api.models

import com.matb.ordering.api.models.entities.Chef
import com.matb.ordering.api.models.entities.Customer
import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.requests.SignupRequest
import com.matb.ordering.api.models.requests.creation.FoodRequest
import com.matb.ordering.api.models.requests.creation.VendorRequest
import com.matb.ordering.api.models.responses.JwtResponse
import com.matb.ordering.api.models.responses.VendorResponse
import com.matb.ordering.api.security.services.UserDetails

fun VendorRequest.toVendor() = Vendor(
        name = name,
        username = username,
        password = password,
        state = ActivityState.ACTIVE,
        role = "ROLE_VENDOR"
)
fun Vendor.toVendorResponse() = VendorResponse(
        state = state,
        name = name,
        foodList = foodList
)
fun FoodRequest.toFood(vendor: Vendor) = Food(
        vendor = vendor,
        name = name,
        price = price,
        image = image
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