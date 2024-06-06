package com.petid.auth.oauth.sdk.controller;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import com.petid.auth.jwt.TokenValidator;
import com.petid.auth.oauth.sdk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/token")
public class TokenController {

    private final AuthService authService;
    private final TokenValidator tokenValidator;

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshToken(
            @RequestParam("refreshToken") String refreshToken
    ) {
        if (tokenValidator.isTokenNotValid(refreshToken)) {
            throw new CustomAuthException(CustomAuthExceptionType.WRONG_TOKEN);
        }

        String uid = tokenValidator.getUidFromToken(refreshToken);

        return ResponseEntity.ok(
                authService.refreshToken(
                        uid
                )
        );
    }
}
