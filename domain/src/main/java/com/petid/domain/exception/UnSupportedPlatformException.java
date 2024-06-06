package com.petid.domain.exception;

public class UnSupportedPlatformException extends RuntimeException {

    public UnSupportedPlatformException(String platform) {
        super("Illegal OAuth2 Platform : " + platform);
    }
}
