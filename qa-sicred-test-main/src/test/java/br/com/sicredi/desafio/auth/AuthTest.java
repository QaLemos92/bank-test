package br.com.sicredi.desafio.auth;

import br.com.sicredi.desafio.client.AuthClient;
import br.com.sicredi.desafio.fixture.TestUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class AuthTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    @DisplayName("Should authenticate user with valid credentials")
    void shouldAuthenticateUserWithValidCredentials() {
        authClient.login(TestUsers.validUser())
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("username", equalTo("emilys"))
            .body("accessToken", notNullValue())
            .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Should reject authentication with invalid credentials")
    void shouldRejectAuthenticationWithInvalidCredentials() {
        authClient.login(TestUsers.invalidUser())
            .then()
            .statusCode(400)
            .body("message", equalTo("Invalid credentials"));
    }
}

