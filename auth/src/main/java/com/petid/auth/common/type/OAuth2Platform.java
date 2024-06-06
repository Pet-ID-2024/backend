package com.petid.auth.common.type;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OAuth2Platform {
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    APPLE("apple");

    final String platform;

    public static OAuth2Platform fromString(String platform) {
        return Arrays.stream(OAuth2Platform.values())
                .filter(p -> p.platform.equalsIgnoreCase(platform))
                .findFirst()
                .orElseThrow(
                        () -> new CustomAuthException(CustomAuthExceptionType.REGISTRATION_ID_NOT_FOUND)
                );
    }

    public boolean isNeedBearer() {
        return this == NAVER;
    }
}
