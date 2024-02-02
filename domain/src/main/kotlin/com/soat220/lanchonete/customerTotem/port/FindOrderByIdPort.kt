package com.soat220.lanchonete.customerTotem.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Result

interface FindOrderByIdPort {
    fun execute(orderId: Long): Result<Order, DomainException>
}