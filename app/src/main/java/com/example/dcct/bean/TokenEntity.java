package com.example.dcct.bean;

public class TokenEntity {
    private String token;

    @Override
    public String toString() {
        return "TokenEntity{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
