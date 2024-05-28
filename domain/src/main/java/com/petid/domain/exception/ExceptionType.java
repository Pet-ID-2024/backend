package com.petid.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionType {

    MEMBER_NOT_FOUND(404, "MB-0001", "멤버를 찾을 수 없습니다.")
    ;

    final int code;
    final String errorCode;
    final String message;
}
