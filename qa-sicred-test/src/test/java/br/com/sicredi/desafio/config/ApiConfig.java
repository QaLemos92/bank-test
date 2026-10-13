package br.com.sicredi.desafio.config;

public final class ApiConfig {

    public static final String BASE_URI = System.getProperty("base.uri", "https://dummyjson.com");

    private ApiConfig() {
    }
}

