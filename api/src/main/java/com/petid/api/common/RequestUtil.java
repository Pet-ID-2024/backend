package com.petid.api.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

    public static long getMemberIdFromRequest(
            HttpServletRequest request
    ) {
        return (request.getParameter("id") != null)
                ? Long.parseLong(request.getParameter("id"))
                : Long.parseLong(request.getAttribute("id").toString());
    }
}
