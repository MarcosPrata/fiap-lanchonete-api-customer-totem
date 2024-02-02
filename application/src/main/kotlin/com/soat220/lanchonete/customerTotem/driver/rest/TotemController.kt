package com.soat220.lanchonete.customerTotem.driver.rest

import com.soat220.lanchonete.customerTotem.usecase.ListProducts
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.customerTotem.driver.rest.dto.request.CreateOrderRequest
import com.soat220.lanchonete.customerTotem.usecase.CreateOrder
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/customer-totem"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TotemController(
    private val createOrder: CreateOrder,
    private val listProducts: ListProducts,
) {
    @PostMapping("/orders")
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest) =
        this.createOrder.execute(createOrderRequest.toDomain()).orThrow()

    @GetMapping("/products")
    fun findProducts() = listProducts.execute().orThrow()
}