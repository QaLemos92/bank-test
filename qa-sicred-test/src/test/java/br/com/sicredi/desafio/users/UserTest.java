package br.com.sicredi.desafio.users;

import br.com.sicredi.desafio.client.UserClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

class UserTest {

    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Should list users with credentials required for authentication")
    void shouldListUsersWithCredentialsRequiredForAuthentication() {
        userClient.listUsers(1)
            .then()
            .statusCode(200)
            .body("users", hasSize(1))
            .body("users[0].id", equalTo(1))
            .body("users[0].username", notNullValue())
            .body("users[0].password", notNullValue())
            .body("total", greaterThan(0))
            .body("limit", equalTo(1));
    }
}

