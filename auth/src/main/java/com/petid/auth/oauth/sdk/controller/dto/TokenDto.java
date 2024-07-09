package com.petid.auth.oauth.sdk.controller.dto;

public record TokenDto(
        String accessToken,
        String refreshToken
) {
}
