package com.matb.ordering.api.models.requests

import com.matb.ordering.api.models.entities.CartItem
import javax.persistence.Id

class CartCreationRequest(
  val orderedFoodList: List<CartItem>,
  val customerId: Int,
  val vendorId: Int
)