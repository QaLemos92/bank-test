package br.com.sicredi.desafio.client;

import br.com.sicredi.desafio.config.ApiConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class BaseClient {

    protected RequestSpecification request() {
        return given()
            .baseUri(ApiConfig.BASE_URI);
    }

    protected RequestSpecification jsonRequest() {
        return request()
            .contentType("application/json");
    }
}

