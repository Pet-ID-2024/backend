package com.petid.auth.oauth.sdk.controller;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.domain.exception.UnSupportedPlatformException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuth2UserInfoUriConverter {

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String naverUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoUri;

    public String convertUserInfoUri(OAuth2Platform platform) {
        return switch (platform) {
            case GOOGLE -> googleUserInfoUri;
            case KAKAO -> kakaoUserInfoUri;
            case NAVER -> naverUserInfoUri;
            default -> throw new UnSupportedPlatformException(platform.name());
        };
    }
}
