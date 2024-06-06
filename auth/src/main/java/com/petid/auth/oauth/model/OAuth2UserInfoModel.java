package com.petid.auth.oauth.model;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import com.petid.domain.member.model.Member;
import com.petid.domain.type.Role;

import java.util.Map;

public record OAuth2UserInfoModel(
        String sub,
        String name,
        String email
) {

    public static OAuth2UserInfoModel of(
            String registrationId,
            Map<String, Object> attributes
    ) {
        return switch (registrationId) {
            case "google" -> fromGoogle(attributes);
            case "kakao" -> fromKakao(attributes);
            case "naver" -> fromNaver(attributes);
            case "apple" -> fromApple(attributes);
            default -> throw new CustomAuthException(CustomAuthExceptionType.REGISTRATION_ID_NOT_FOUND);
        };
    }

    private static OAuth2UserInfoModel fromGoogle(
            Map<String, Object> attributes
    ) {
        return new OAuth2UserInfoModel(
                (String) attributes.get("id"),
                (String) attributes.get("name"),
                (String) attributes.get("email")
        );
    }

    private static OAuth2UserInfoModel fromKakao(
            Map<String, Object> attributes
    ) {
        @SuppressWarnings("unchecked") Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        @SuppressWarnings("unchecked") Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return new OAuth2UserInfoModel(
                String.valueOf(attributes.get("id")),
                (String) profile.get("nickname"),
                (String) account.get("email")
        );
    }

    private static OAuth2UserInfoModel fromNaver(
            Map<String, Object> attributes
    ) {
        @SuppressWarnings("unchecked") Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return new OAuth2UserInfoModel(
                (String) response.get("id"),
                (String) response.get("nickname"),
                (String) response.get("email")
        );
    }

    private static OAuth2UserInfoModel fromApple(
            Map<String, Object> attributes
    ) {
        // TODO
        return null;
    }

    public Member toDomain(
            String platform
    ) {
        return new Member(
                null,
                sub,
                platform,
                email,
                Role.ROLE_USER
        );
    }
}
