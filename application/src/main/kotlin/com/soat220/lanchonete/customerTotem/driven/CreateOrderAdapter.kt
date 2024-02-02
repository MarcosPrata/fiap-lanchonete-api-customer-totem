package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderItemRepository
import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.driven.postgresdb.model.OrderItem
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.exception.CreateOrderException
import com.soat220.lanchonete.customerTotem.port.CreateOrderPort
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Order as DomainOrder

@Service
class CreateOrderAdapter(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) : CreateOrderPort {
    override fun execute(order: DomainOrder): Result<DomainOrder, DomainException> {
        return try {
            val orderItensTosave = order.orderItems.map { OrderItem.fromDomain(it) }

            order.orderItems = mutableListOf()

            val savedOrder = orderRepository.saveAndFlush(Order.fromDomain(order))

            val savedOrderItens = orderItemRepository.saveAllAndFlush(
                orderItensTosave.map {
                    it.order = savedOrder
                    it
                }
            )

            savedOrder.orderItems = savedOrderItens

            Success(savedOrder.toDomain())
        } catch (e: Exception) {
            Failure(
                CreateOrderException(
                    order,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}