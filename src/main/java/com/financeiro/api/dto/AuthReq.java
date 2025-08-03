package com.financeiro.api.dto;

public record AuthReq(

        String email,
        String password

) {
    public AuthReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
