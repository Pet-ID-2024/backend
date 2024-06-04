package com.petid.auth.oauth.sdk.controller;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.oauth.sdk.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2/authorization/token")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @PostMapping("/{platform}")
    public ResponseEntity<TokenDto> oauth2Login(
            @PathVariable("platform") String platform,
            @RequestParam("token") String token
    ) {
        OAuth2Platform platformEnum = OAuth2Platform.fromString(platform);
        if (platform.equals(platformEnum.getPlatform())) token = "Bearer " + token;

        return ResponseEntity.ok(
                oAuth2Service.getUserInfo(
                        platformEnum,
                        token
                )
        );
    }
}
