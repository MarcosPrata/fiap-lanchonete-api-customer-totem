package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.NotFoundException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.OrderItem
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.port.*
import com.soat220.lanchonete.customerTotem.usecase.dto.CreateOrder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soat220.lanchonete.common.config.LocalDateTimeTypeAdapter
import java.time.LocalDateTime
import javax.inject.Named
import com.soat220.lanchonete.common.model.Customer as DomainCustomer

@Named
class CreateOrder(
    private val findCustomerByCpfPort: FindCustomerByCpfPort,
    private val findProductByIdPort: FindProductByIdPort,
    private val createOrderPort: CreateOrderPort,
    private val createCustomerPort: CreateCustomerPort,
    private val sendOrderQueuePort: SendOrderQueuePort
) {

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
        .create()

    fun execute(createOrder: CreateOrder): Result<Order, DomainException> {
        val orderItems = createOrder.orderItems.map {
            val product = findProductByIdPort.execute(it.productId).getOrNull()
                ?: throw NotFoundException(Product::class.java)

            OrderItem(
                product = product,
                amount = it.amount.toInt()
            )
        }.toMutableList()

        val customer = getCustomer(createOrder)

        verifyPayment(orderItems)

        val order = Order(
            customer = customer,
            notes = createOrder.notes ?: "",
            orderItems = orderItems,
            orderStatus = OrderStatus.RECEIVED,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val orderModel = createOrderPort.execute(order)

        this.sendOrderQueuePort.send(gson.toJson(orderModel.getOrNull()))

        return orderModel
    }

    private fun getCustomer(createOrder: CreateOrder): DomainCustomer? {
        var customer: DomainCustomer? = null

        if (createOrder.customer != null) {
            val existentCustomer = findCustomerByCpfPort.execute(createOrder.customer.cpf)

            if (existentCustomer.getOrNull() != null) {
                customer = existentCustomer.getOrNull()
            } else {
                customer = createCustomerPort.execute(
                    DomainCustomer(
                        cpf = createOrder.customer.cpf,
                        name = createOrder.customer.name,
                        email = createOrder.customer.email
                    )
                ).getOrNull()
            }
        }

        return customer
    }

    private fun verifyPayment(orderItems: MutableList<OrderItem>) {
        val totalAmount = orderItems
            .map { it.product.price * it.amount }
            .reduce { acc, price -> acc + price }

//        val paymentStatus =
//            if (processPaymentPort.execute(order, totalAmount)) PaymentStatus.APPROVED
//            else PaymentStatus.DECLINED
//
//        createPaymentPort.execute(order, paymentStatus, totalAmount).orThrow()
//
//        if (paymentStatus != PaymentStatus.APPROVED) {
//            throw PaymentNotApprovedException("Payment not approved", ErrorCode.PAYMENT_NOT_APPROVED)
//        }
    }
}