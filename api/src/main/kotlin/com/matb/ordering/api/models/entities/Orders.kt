package com.matb.ordering.api.models.entities

import com.matb.ordering.api.models.OrderState
import com.matb.ordering.api.models.entities.base.BaseEntity
import javax.persistence.*

@Entity
data class Orders(
    var total: Int = 0,
    var customerId: Int = 0,
    //@OneToMany(mappedBy = "orders")
    //var orderItem: Set<OrderItem> = HashSet(),
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "vendor_id")
    var vendor: Vendor? = null,
    var orderState: OrderState? = null
) : BaseEntity()
