package com.petid.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomAuthExceptionType {
    REGISTRATION_ID_NOT_FOUND(404, "AT-0000", "소셜 로그인 플랫폼을 찾을 수 없습니다.");

    final int code;
    final String errorCode;
    final String message;
}
