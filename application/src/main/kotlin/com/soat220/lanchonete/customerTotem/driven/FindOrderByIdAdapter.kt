package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.NotFoundException
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.FindOrderByIdPort
import org.springframework.stereotype.Service

@Service
class FindOrderByIdAdapter(
    private val orderRepository: OrderRepository
) : FindOrderByIdPort {

    override fun execute(orderId: Long): Result<com.soat220.lanchonete.common.model.Order, DomainException> {
        return try {
            val order = orderRepository.findById(orderId)
                .orElseThrow { NotFoundException(com.soat220.lanchonete.common.model.Order::class.java) }
            Success(order.toDomain())
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }

}