package com.soat220.lanchonete.common.driven

import com.fasterxml.jackson.databind.ObjectMapper
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.exception.FindProductByIdException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.customerTotem.port.FindProductByIdPort
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class FindProductByIdAdapter(
    private val productRepository: ProductRepository
) : FindProductByIdPort {
    override fun execute(productId: Long): Result<DomainProduct?, DomainException> {
        return try {
            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create("http://erp:83/api/erp/products/$productId")) // TODO set url by value
                .build();

            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Success(ObjectMapper().readValue(response.body(), Product::class.java))
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