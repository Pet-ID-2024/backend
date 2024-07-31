package com.petid.auth.oauth.sdk.service;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.oauth.sdk.controller.dto.TokenDto;

public interface AuthService {
    TokenDto getUserInfo(OAuth2Platform platform, String fcmToken, String token, boolean advertisement);

    TokenDto refreshToken(String uid);

    TokenDto login(String uid, String fcmToken);
}
