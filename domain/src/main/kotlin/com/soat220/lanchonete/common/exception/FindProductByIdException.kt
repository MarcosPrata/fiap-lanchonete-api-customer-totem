package com.soat220.lanchonete.common.exception

class FindProductByIdException(
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error searching product by id",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        productId: Long? = null,
        details: List<DomainException>
    ) : this() {
        this.message = "error searching product by id $productId"
        this.details = details
    }
}