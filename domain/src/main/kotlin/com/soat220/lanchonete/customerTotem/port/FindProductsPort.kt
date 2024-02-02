package com.soat220.lanchonete.customerTotem.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result

interface FindProductsPort {
    fun execute(): Result<List<Product>, DomainException>
}