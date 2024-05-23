package com.petid.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomAuthException extends RuntimeException {
    final CustomAuthExceptionType exceptionType;
}
