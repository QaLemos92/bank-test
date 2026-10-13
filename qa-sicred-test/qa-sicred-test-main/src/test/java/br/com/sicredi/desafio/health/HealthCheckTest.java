package br.com.sicredi.desafio.health;

import br.com.sicredi.desafio.config.ApiConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class HealthCheckTest {

    @Test
    @DisplayName("Should return API health status successfully")
    void shouldReturnApiHealthStatusSuccessfully() {
        given()
            .baseUri(ApiConfig.BASE_URI)
        .when()
            .get("/test")
        .then()
            .statusCode(200)
            .body("status", equalTo("ok"))
            .body("method", equalTo("GET"));
    }
}

