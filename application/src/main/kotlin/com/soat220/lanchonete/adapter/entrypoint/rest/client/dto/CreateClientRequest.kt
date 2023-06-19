package com.soat220.lanchonete.adapter.entrypoint.rest.client.dto

import com.soat220.lanchonete.client.model.Client

class CreateClientRequest (

    private val name: String,
    private val cpf: String

){

    fun toModel() = Client (
        null,
        name = name,
        cpf = cpf
    )

}