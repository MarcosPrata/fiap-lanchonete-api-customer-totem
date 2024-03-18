package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.customerTotem.port.SendOrderQueuePort
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SendOrderQueueAdapter(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${exchange.pedidos.registrados}") private val ordersExchange: String
) : SendOrderQueuePort {

    override fun send(order: String) {
        rabbitTemplate.setExchange(ordersExchange)
        rabbitTemplate.convertAndSend(order)
    }
}