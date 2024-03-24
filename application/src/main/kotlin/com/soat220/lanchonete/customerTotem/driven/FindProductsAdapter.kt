package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.AbstractHttpClientService
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.FindProductsPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class FindProductsAdapter(
    @Value("\${url.erp}") private val erpHost: String
) : FindProductsPort {
    override fun execute(): Result<List<DomainProduct>?, DomainException> {
        return try {

            val httpClientService = AbstractHttpClientService<DomainProduct>()

            Success(httpClientService.getForList("$erpHost:83/api/erp/products"))

        } catch (e: Exception) {
            Failure(DomainException(e, ErrorCode.DATABASE_ERROR))
        }
    }
}