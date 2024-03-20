package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.AbstractHttpClientService
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.CreateCustomerPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Customer as DomainCustomer

@Service
class CreateCustomerAdapter(
    @Value("\${url.erp}") private val erpHost: String
) : CreateCustomerPort {

    override fun execute(customer: DomainCustomer): Result<DomainCustomer?, DomainException> {
        return try {

            val httpClientService = AbstractHttpClientService<DomainCustomer>()

            Success(httpClientService.post("$erpHost:83/api/erp/customers", customer))
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.CREATE_CUSTOMER_ERROR)
            )
        }
    }
}
