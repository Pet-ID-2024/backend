package com.petid.auth.jwt;

import com.petid.auth.exception.CustomAuthException;
import com.petid.auth.exception.CustomAuthExceptionType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);

        if (!tokenProvider.validateToken(token)) throw new CustomAuthException(CustomAuthExceptionType.WRONG_TOKEN);
        request.setAttribute("uid", tokenProvider.getUidFromAccessToken(token));

        filterChain.doFilter(request, response);
    }
}

