package br.com.sicredi.desafio.fixture;

import br.com.sicredi.desafio.model.ProductRequest;

public final class TestProducts {

    private TestProducts() {
    }

    public static ProductRequest validProduct() {
        return new ProductRequest(
            "Sicredi Test Product",
            99.90,
            10,
            "electronics"
        );
    }
}

