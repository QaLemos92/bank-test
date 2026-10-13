package br.com.sicredi.desafio.products;

import br.com.sicredi.desafio.client.AuthClient;
import br.com.sicredi.desafio.client.ProductClient;
import br.com.sicredi.desafio.fixture.TestUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

class AuthenticatedProductTest {

    private final AuthClient authClient = new AuthClient();
    private final ProductClient productClient = new ProductClient();

    @Test
    @DisplayName("Should list authenticated products with valid access token")
    void shouldListAuthenticatedProductsWithValidAccessToken() {
        String accessToken = authClient.getAccessToken(TestUsers.validUser());

        productClient.listAuthenticatedProducts(accessToken, 1)
            .then()
            .statusCode(200)
            .body("products", hasSize(1))
            .body("total", greaterThan(0))
            .body("limit", equalTo(1));
    }

    @Test
    @DisplayName("Should require access token to list authenticated products")
    void shouldRequireAccessTokenToListAuthenticatedProducts() {
        productClient.listAuthenticatedProductsWithoutToken()
            .then()
            .statusCode(401)
            .body("message", equalTo("Access Token is required"));
    }

    @Test
    @DisplayName("Should reject invalid access token to list authenticated products")
    void shouldRejectInvalidAccessTokenToListAuthenticatedProducts() {
        productClient.listAuthenticatedProductsWithRawToken("invalid-token")
            .then()
            .statusCode(401)
            .body("message", equalTo("Invalid/Expired Token!"));
    }
}

