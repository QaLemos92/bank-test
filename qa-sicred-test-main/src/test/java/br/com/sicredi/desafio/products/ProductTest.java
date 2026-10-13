package br.com.sicredi.desafio.products;

import br.com.sicredi.desafio.client.ProductClient;
import br.com.sicredi.desafio.fixture.TestProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

class ProductTest {

    private final ProductClient productClient = new ProductClient();

    @Test
    @DisplayName("Should list products successfully")
    void shouldListProductsSuccessfully() {
        productClient.listProducts(1)
            .then()
            .statusCode(200)
            .body("products", hasSize(1))
            .body("products[0].id", notNullValue())
            .body("products[0].title", notNullValue())
            .body("total", greaterThan(0))
            .body("skip", equalTo(0))
            .body("limit", equalTo(1));
    }

    @Test
    @DisplayName("Should return product by existing id")
    void shouldReturnProductByExistingId() {
        productClient.getProductById(1)
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", equalTo("Essence Mascara Lash Princess"))
            .body("price", notNullValue())
            .body("stock", notNullValue());
    }

    @Test
    @DisplayName("Should return not found for non-existent product id")
    void shouldReturnNotFoundForNonExistentProductId() {
        productClient.getProductById(999999)
            .then()
            .statusCode(404)
            .body("message", equalTo("Product with id '999999' not found"));
    }

    @Test
    @DisplayName("Should add product successfully")
    void shouldAddProductSuccessfully() {
        productClient.addProduct(TestProducts.validProduct())
            .then()
            .statusCode(201)
            .body("id", equalTo(195))
            .body("title", equalTo("Sicredi Test Product"))
            .body("price", equalTo(99.90F))
            .body("stock", equalTo(10))
            .body("category", equalTo("electronics"));
    }
}

