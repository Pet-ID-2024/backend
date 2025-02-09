package com.petid.auth.oauth.sdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.auth.common.type.OAuth2Platform;
import com.petid.auth.jwt.TokenProvider;
import com.petid.auth.jwt.TokenValidator;
import com.petid.auth.oauth.model.OAuth2UserInfoModel;
import com.petid.auth.oauth.sdk.controller.OAuth2UserInfoUriConverter;
import com.petid.auth.oauth.sdk.controller.dto.TokenDto;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.domain.member.repository.MemberAuthRepository;
import com.petid.domain.member.repository.MemberPolicyRepository;
import com.petid.domain.member.repository.MemberRepository;
import com.petid.domain.member.util.RandomNameUtil;
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

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final MemberManager memberManager;
    private final MemberRepository memberRepository;
    private final MemberPolicyRepository memberPolicyRepository;
    private final TokenProvider tokenProvider;
    private final TokenValidator tokenValidator;
    private final OAuth2UserInfoUriConverter oauth2Converter;
    private final MemberAuthRepository memberAuthRepository;
    private final RandomNameUtil randomNameUtil;

    @Override
    public TokenDto join(
            OAuth2Platform platform,
            String fcmToken,
            String token,
            boolean advertisement
    ) {
        OAuth2UserInfoModel oAuth2UserInfo =
                (platform.getPlatform().equals("google") || platform.getPlatform().equals("apple"))
                        ? parseSubFromToken(token)
                        : requestToAuthServer(token, platform);

        Member member = memberManager.getOrSave(oAuth2UserInfo.toDomain(platform.getPlatform(), fcmToken));
        memberAuthRepository.save(MemberAuthInfo.createDefaultMemberAuth(member.id(), randomNameUtil.getRandomName()));
        memberPolicyRepository.save(MemberPolicy.of(member, advertisement));

        String accessToken = tokenProvider.getAccessToken(member);
        String refreshToken = tokenProvider.getRefreshToken(member);

        return new TokenDto(
                "Bearer " + accessToken,
                "Bearer " + refreshToken
        );
    }

    @Override
    public TokenDto refreshToken(
            long memberId
    ) {
        Member member = memberManager.get(memberId);

        String newAccessToken = tokenProvider.getAccessToken(member);
        String newRefreshToken = tokenProvider.getRefreshToken(member);

        return new TokenDto(
                newAccessToken,
                newRefreshToken
        );
    }

    @Override
    public TokenDto login(
            String uid,
            String fcmToken
    ) {
        Member member = memberManager.getByUid(uid);
        memberRepository.save(member.updateFcmToken(fcmToken));

        String accessToken = tokenProvider.getAccessToken(member);
        String refreshToken = tokenProvider.getRefreshToken(member);

        return new TokenDto(
                "Bearer " + accessToken,
                "Bearer " + refreshToken
        );
    }

    private OAuth2UserInfoModel requestToAuthServer(
            String token,
            OAuth2Platform platform
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(oauth2Converter.convertUserInfoUri(platform))
                .queryParam("access_token", token);

        // 제네릭 타입정보를 런타임에 유지
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<>() {
        };

        Map<String, Object> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                responseType
        ).getBody();

        return OAuth2UserInfoModel.of(
                platform.getPlatform(),
                response
        );
    }

    private OAuth2UserInfoModel parseSubFromToken(
            String token
    ) {
        String sub = tokenValidator.getSubFromIdToken(token);

        return OAuth2UserInfoModel.of(
                sub,
                null,
                null
        );
    }
}
