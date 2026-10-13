package br.com.sicredi.desafio.client;

import br.com.sicredi.desafio.model.LoginRequest;
import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    public Response login(LoginRequest loginRequest) {
        return jsonRequest()
            .body(loginRequest)
        .when()
            .post("/auth/login");
    }

    public String getAccessToken(LoginRequest loginRequest) {
        return login(loginRequest)
            .then()
            .statusCode(200)
            .extract()
            .path("accessToken");
    }
}

