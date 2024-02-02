package com.soat220.lanchonete.common.exception

class FindProductsException(
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error searching products",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        details: List<DomainException>
    ) : this() {
        this.details = details
    }
}