package com.soat220.lanchonete.customerTotem.port

import com.soat220.lanchonete.common.model.Order

interface SendOrderQueuePort {

    fun send(order: String)
}