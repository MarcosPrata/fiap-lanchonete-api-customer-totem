package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.exception.FindProductsException
import com.soat220.lanchonete.customerTotem.port.FindProductsPort
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class FindProductsAdapter(
    private val productRepository: ProductRepository
) : FindProductsPort {
    override fun execute(): Result<List<DomainProduct>, DomainException> {
        return try {
            val products = productRepository.findAll() //TODO GET para ERP/PRODUCTS

            Success(products.map { it.toDomain() })
        } catch (e: Exception) {
            Failure(
                FindProductsException(listOf(DomainException(e, ErrorCode.DATABASE_ERROR)))
            )
        }
    }
}