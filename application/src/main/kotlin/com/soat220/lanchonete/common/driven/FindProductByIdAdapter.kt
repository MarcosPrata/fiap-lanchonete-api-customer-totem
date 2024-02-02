package com.soat220.lanchonete.common.driven
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.exception.FindProductByIdException
import com.soat220.lanchonete.customerTotem.port.FindProductByIdPort
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class FindProductByIdAdapter(
    private val productRepository: ProductRepository
) : FindProductByIdPort {
    override fun execute(productId: Long): Result<DomainProduct?, DomainException> {
        return try {
            val product = productRepository.findById(productId)

            Success(product.get().toDomain())
        } catch (e: Exception) {
            Failure(
                FindProductByIdException(
                    productId,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}