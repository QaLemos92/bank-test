package br.com.sicredi.desafio.client;

import br.com.sicredi.desafio.model.ProductRequest;
import io.restassured.response.Response;

public class ProductClient extends BaseClient {

    public Response listProducts(int limit) {
        return request()
            .queryParam("limit", limit)
        .when()
            .get("/products");
    }

    public Response getProductById(int id) {
        return request()
        .when()
            .get("/products/{id}", id);
    }

    public Response addProduct(ProductRequest productRequest) {
        return jsonRequest()
            .body(productRequest)
        .when()
            .post("/products/add");
    }

    public Response listAuthenticatedProducts(String accessToken, int limit) {
        return request()
            .header("Authorization", "Bearer " + accessToken)
            .queryParam("limit", limit)
        .when()
            .get("/auth/products");
    }

    public Response listAuthenticatedProductsWithoutToken() {
        return request()
        .when()
            .get("/auth/products");
    }

    public Response listAuthenticatedProductsWithRawToken(String token) {
        return request()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/auth/products");
    }
}
