package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.AbstractHttpClientService
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.exception.FindProductByIdException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.FindProductByIdPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class FindProductByIdAdapter(
    @Value("\${url.erp}") private val erpHost: String
) : FindProductByIdPort {
    override fun execute(productId: Long): Result<DomainProduct?, DomainException> {
        return try {

            val httpClientService = AbstractHttpClientService<Product>()

            Success(httpClientService.get("$erpHost:83/api/erp/products/$productId"))
        } catch (e: Exception) {
            Failure(
                FindProductByIdException(
                    productId,
                    listOf(DomainException(e, ErrorCode.ENTITY_NOT_FOUND_ERROR))
                )
            )
        }
    }
}