package com.petid.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenAuthExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

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
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("timestamp", Instant.now().toEpochMilli());
            errorResponse.put("status", HttpServletResponse.SC_FORBIDDEN);
            errorResponse.put("error", exceptionType.getMessage());
            errorResponse.put("path", request.getRequestURI());

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
}
