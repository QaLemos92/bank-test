package br.com.sicredi.desafio.fixture;

import br.com.sicredi.desafio.model.LoginRequest;

public final class TestUsers {

    private TestUsers() {
    }

    public static LoginRequest validUser() {
        return new LoginRequest("emilys", "emilyspass");
    }

    public static LoginRequest invalidUser() {
        return new LoginRequest("invalid_user", "invalid_password");
    }
}
