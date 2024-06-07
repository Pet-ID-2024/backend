package com.petid.auth.jwt;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TokenAuthExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomAuthException e) {
            CustomAuthExceptionType exceptionType = e.getExceptionType();
            response.sendError(exceptionType.getCode(), e.getMessage());
        }
    }
}
