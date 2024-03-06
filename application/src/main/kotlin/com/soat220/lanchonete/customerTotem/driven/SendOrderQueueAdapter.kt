package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.customerTotem.port.SendOrderQueuePort
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SendOrderQueueAdapter(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${queue.pedidos.registrados}") private val queue: String
) : SendOrderQueuePort {

    override fun send(order: String) {
        rabbitTemplate.convertAndSend(queue, order)
    }
}