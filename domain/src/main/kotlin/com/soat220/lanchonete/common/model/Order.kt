package com.soat220.lanchonete.common.model

import com.soat220.lanchonete.common.model.enums.OrderStatus
import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    var customer: Customer? = null,
    var orderItems: MutableList<OrderItem>,
    var orderStatus: OrderStatus,
    var notes: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)
