package com.petid.api.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

    public static long getMemberIdFromRequest(HttpServletRequest request) {
        return Long.parseLong(request.getAttribute("id").toString());
    }
}
