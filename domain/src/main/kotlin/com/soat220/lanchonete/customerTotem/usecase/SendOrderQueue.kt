package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.customerTotem.port.SendOrderQueuePort

class SendOrderQueue(
    private val sendOrderQueuePort: SendOrderQueuePort
) {

    fun execute(order: String) {
        this.sendOrderQueuePort.send(order)
    }
}