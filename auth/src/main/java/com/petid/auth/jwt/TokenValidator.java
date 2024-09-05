package com.petid.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public boolean isTokenNotValid(String token) {
        if (token == null || token.isEmpty()) return true;

        token = token.replace("Bearer ", "");

        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        } catch (TokenExpiredException e) {
            throw new CustomAuthException(CustomAuthExceptionType.TOKEN_EXPIRED);
        }

        if (!decodedJWT.getClaim("role").asString().startsWith("ROLE_")) return true;

        String tokenType = decodedJWT.getClaim("tokenType").asString();
        return !tokenType.equals("ACCESS_TOKEN") && !tokenType.equals("REFRESH_TOKEN");
    }

    public String getUidFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("sub").asString();
    }
}
