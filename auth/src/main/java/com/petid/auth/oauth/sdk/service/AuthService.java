package com.petid.auth.oauth.sdk.service;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.oauth.sdk.controller.TokenDto;

public interface AuthService {
    TokenDto getUserInfo(OAuth2Platform platform, String token);

    TokenDto refreshToken(String uid);
}
