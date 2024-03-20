package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.AbstractHttpClientService
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.FindCustomerByCpfPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FindCustomerByCpfAdapter(
    @Value("\${url.erp}") private val erpHost: String
) : FindCustomerByCpfPort {
    override fun execute(cpf: String?): Result<Customer?, DomainException> {
        return try {

            val httpClientService = AbstractHttpClientService<Customer>()

            Success(httpClientService.get("$erpHost:83/api/erp/customers/find?cpf=$cpf"))

        } catch (e: Exception) {
            Failure(DomainException(e, ErrorCode.ENTITY_NOT_FOUND_ERROR))
        }
    }
}