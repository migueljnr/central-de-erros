package com.projetofinal.security;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticationResponse {

    @ApiModelProperty("O token gerado.")
    private String token;
    @ApiModelProperty("O data em que o token expira, expresso em milissegundos, desde 1 janeiro 1970.")
    private long expirationTime;

    public AuthenticationResponse(String token, long expirationTime) {
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
}
