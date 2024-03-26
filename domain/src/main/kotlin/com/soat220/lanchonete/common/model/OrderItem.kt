package com.soat220.lanchonete.common.model

class OrderItem(
    val id: Long? = null,
    val product: Product?,
    val amount: Int = 1,
)