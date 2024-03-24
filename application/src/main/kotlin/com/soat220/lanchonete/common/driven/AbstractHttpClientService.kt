package com.soat220.lanchonete.common.driven

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class AbstractHttpClientService<model> {

    inline fun <reified model> get(url: String): model? {

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .uri(URI.create(url))
            .build();

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        // prevenir mapper exception
        if (response.statusCode() == HttpStatus.OK.value() ) {
            return ObjectMapper().readValue(response.body(), model::class.java)
        }

        return null;
    }

    inline fun <reified model> getForList(url: String): List<model>? {

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .uri(URI.create(url))
            .build();

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        // prevenir mapper exception
        if (response.statusCode() == HttpStatus.OK.value() ) {
            val typeRef: TypeReference<List<model>> = object : TypeReference<List<model>>() {}
            return ObjectMapper().readValue(response.body(), typeRef)
        }

        return null;
    }

    inline fun <reified model> post(url: String, requestBody: Any): model? {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .POST(HttpRequest.BodyPublishers.ofString(ObjectMapper().writeValueAsString(requestBody)))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        // prevenir mapper exception
        if (response.statusCode() == HttpStatus.OK.value()
            || response.statusCode() == HttpStatus.CREATED.value()) {
            return ObjectMapper().readValue(response.body(), model::class.java)
        }

        return null
    }
}