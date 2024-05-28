package com.petid.api.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasicResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> BasicResponse<T> ok(T data) {
        return new BasicResponse<>(
                200,
                "OK",
                data
        );
    }
}
