package com.petid.api.config;

import com.petid.auth.jwt.TokenProvider;
import com.petid.auth.oauth.sdk.controller.OAuth2UserInfoUriConverter;
import com.petid.domain.config.FcmInitializer;
import com.petid.domain.email.EmailService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetIdAPITestConfig {
    public static final String TEST_TOKEN = "testToken";

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private OAuth2UserInfoUriConverter oAuth2UserInfoUriConverter;

    @MockBean
    private EmailService emailService;

    @MockBean
    private FcmInitializer fcmInitializer;
}
