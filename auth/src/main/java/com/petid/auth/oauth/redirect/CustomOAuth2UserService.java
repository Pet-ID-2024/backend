package com.petid.auth.oauth.redirect;

import com.petid.auth.oauth.model.OAuth2UserInfoModel;
import com.petid.auth.oauth.model.PrincipalDetails;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.manager.MemberManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberManager memberManager;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        String platform = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfoModel oAuth2UserInfo = OAuth2UserInfoModel.of(platform, oAuth2UserAttributes);

        Member member = memberManager.getOrSave(oAuth2UserInfo.toDomain(platform));

        return new PrincipalDetails(member, oAuth2UserAttributes);
    }
}
