package com.petid.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.petid.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    private static final String ROLE_CLAIM_NAME = "role";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30L;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 15L;

    public String getAccessToken(Authentication authentication) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRE_TIME);

        return createToken(authentication, localDateTime, "ACCESS_TOKEN");
    }

    public String getAccessToken(Member member) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRE_TIME);

        return createToken(member, localDateTime, "ACCESS_TOKEN");
    }

    public String getRefreshToken(Authentication authentication) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRE_TIME);

        return createToken(authentication, localDateTime, "REFRESH_TOKEN");
    }

    public String getRefreshToken(Member member) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRE_TIME);

        return createToken(member, localDateTime, "REFRESH_TOKEN");
    }

    private String createToken(Authentication authentication, LocalDateTime expire, String type) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return JWT.create()
                .withSubject(authentication.getName())
                .withExpiresAt(Date.from(expire.atZone(ZoneId.systemDefault()).toInstant()))
                .withClaim(ROLE_CLAIM_NAME, authorities)
                .withClaim("tokenType", type)
                .sign(Algorithm.HMAC256(secretKey));
    }

    private String createToken(Member member, LocalDateTime expire, String type) {
        return JWT.create()
                .withSubject(member.uid())
                .withExpiresAt(Date.from(expire.atZone(ZoneId.systemDefault()).toInstant()))
                .withClaim(ROLE_CLAIM_NAME, member.role().name())
                .withClaim("tokenType", type)
                .sign(Algorithm.HMAC256(secretKey));
    }
}
