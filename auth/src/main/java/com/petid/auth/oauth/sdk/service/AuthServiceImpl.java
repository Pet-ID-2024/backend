package com.petid.auth.oauth.sdk.service;

import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.jwt.TokenProvider;
import com.petid.auth.oauth.model.OAuth2UserInfoModel;
import com.petid.auth.oauth.sdk.controller.OAuth2UserInfoUriConverter;
import com.petid.auth.oauth.sdk.controller.TokenDto;
import com.petid.domain.member.Member;
import com.petid.domain.member.MemberManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final MemberManager memberManager;
    private final TokenProvider tokenProvider;
    private final OAuth2UserInfoUriConverter oauth2Converter;

    @Override
    public TokenDto getUserInfo(
            OAuth2Platform platform,
            String token
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(oauth2Converter.convertUserInfoUri(platform))
                .queryParam("access_token", token);

        // 제네릭 타입정보를 런타임에 유지
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<>() {};

        Map<String, Object> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                responseType
        ).getBody();

        OAuth2UserInfoModel oAuth2UserInfo = OAuth2UserInfoModel.of(
                platform.getPlatform(),
                response
        );

        Member member = memberManager.getOrSave(oAuth2UserInfo.toDomain(platform.getPlatform()));

        String accessToken = tokenProvider.getAccessToken(member);
        String refreshToken = tokenProvider.getRefreshToken(member);

        return new TokenDto(
                "Bearer " + accessToken,
                "Bearer " + refreshToken
        );
    }

    @Override
    public TokenDto refreshToken(String uid) {
        Member member = memberManager.getByUid(uid);

        String newAccessToken = tokenProvider.getAccessToken(member);
        String newRefreshToken = tokenProvider.getRefreshToken(member);

        return new TokenDto(
                newAccessToken,
                newRefreshToken
        );
    }
}
