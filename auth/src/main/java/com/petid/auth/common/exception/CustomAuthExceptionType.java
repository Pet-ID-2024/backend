package com.petid.auth.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomAuthExceptionType {
    REGISTRATION_ID_NOT_FOUND(404, "AT-0000", "소셜 로그인 플랫폼을 찾을 수 없습니다."),

    WRONG_TOKEN(403, "AT-0001", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(401, "AT-0002", "토큰이 만료되었습니다.");

    final int code;
    final String errorCode;
    final String message;
}
