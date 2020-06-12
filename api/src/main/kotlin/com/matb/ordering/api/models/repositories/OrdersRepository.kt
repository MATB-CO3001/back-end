package com.matb.ordering.api.models.repositories

import com.matb.ordering.api.models.OrderState
import org.springframework.stereotype.Repository
import com.matb.ordering.api.models.entities.Orders
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface OrdersRepository : JpaRepository<Orders, Int>{
    fun findAllByOrderState(orderState: OrderState): List<Orders>
}
