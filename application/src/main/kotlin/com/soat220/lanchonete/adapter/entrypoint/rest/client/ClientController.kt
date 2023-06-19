package com.soat220.lanchonete.adapter.entrypoint.rest.client

import com.soat220.lanchonete.adapter.entrypoint.rest.client.dto.CreateClientRequest
import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.port.driver.DomainServiceInterface
import com.soat220.lanchonete.result.orThrow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/clients"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientController(
    private val clientService: DomainServiceInterface<Client>
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createClientRequest: CreateClientRequest) =
        clientService.save(createClientRequest.toModel()).orThrow()

}