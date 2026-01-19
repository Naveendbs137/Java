package com.dhatvibs.dto;

public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto(String a, String r) {
        this.accessToken = a;
        this.refreshToken = r;
    }

    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
}
