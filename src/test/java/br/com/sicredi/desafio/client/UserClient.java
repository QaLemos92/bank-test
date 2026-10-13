package br.com.sicredi.desafio.client;

import io.restassured.response.Response;

public class UserClient extends BaseClient {

    public Response listUsers(int limit) {
        return request()
            .queryParam("limit", limit)
        .when()
            .get("/users");
    }
}

