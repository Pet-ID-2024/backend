package com.petid.auth.oauth.sdk.controller;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.oauth.sdk.controller.dto.TokenDto;
import com.petid.auth.oauth.sdk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/oauth2")
public class OAuth2Controller {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> oauth2Login(
            @RequestParam("sub") String sub,
            @RequestParam("fcmToken") String fcmToken
    ) {
        return ResponseEntity.ok(
                authService.login(sub, fcmToken)
        );
    }

    @PostMapping("/join/{platform}")
    public ResponseEntity<TokenDto> oauth2Join(
            @PathVariable("platform") String platform,
            @RequestParam("fcmToken") String fcmToken,
            @RequestParam("token") String token,
            @RequestParam("ad") boolean ad
            ) {
        OAuth2Platform platformEnum = OAuth2Platform.fromString(platform);
        if (platformEnum.isNeedBearer()) token = "Bearer " + token;

        return ResponseEntity.ok(
                authService.getUserInfo(
                        platformEnum,
                        fcmToken,
                        token,
                        ad
                )
        );
    }
}
