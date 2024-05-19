package com.petid.auth.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAuthException extends RuntimeException {
    CustomAuthExceptionType exceptionType;
}
