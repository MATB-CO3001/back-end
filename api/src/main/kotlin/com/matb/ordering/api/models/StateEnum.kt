package com.matb.ordering.api.models

enum class ActivityState{
    ACTIVE, UNACTIVE
}
enum class CartState{
    PENDING, INPROGRESS, READY, DONE, REJECTED
}
enum class RoleState {
    ROLE_CUSTOMER, ROLE_CHEF, ROLE_VENDOR
}
enum class FoodState {
    AVAILABLE, UNAVAILABLE
}