package com.petid.auth.oauth.sdk.controller;

public record TokenDto(
        String accessToken,
        String refreshToken
) {
}
