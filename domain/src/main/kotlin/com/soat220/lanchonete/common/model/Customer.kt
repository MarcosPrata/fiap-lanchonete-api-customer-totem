package com.soat220.lanchonete.common.model

data class Customer(
    val id: Long? = null,
    val name: String?,
    val email: String?,
    val cpf: String?
) {
    constructor() : this(null, "", "", "")
}