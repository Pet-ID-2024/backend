package com.petid.auth.jwt;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
import com.petid.auth.oauth.model.PrincipalDetails;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.manager.MemberManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {

    private final TokenValidator tokenValidator;
    private final MemberManager memberManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(AUTHORIZATION);

        // test용
        if (token.equals("test")) {
            request.setAttribute("uid", "test");
            filterChain.doFilter(request, response);
            return;
        }

        if (tokenValidator.isTokenNotValid(token)) throw new CustomAuthException(CustomAuthExceptionType.WRONG_TOKEN);

        String uid = tokenValidator.getUidFromToken(token);
        request.setAttribute("uid", uid);

        Member member = memberManager.getByUid(uid);
        PrincipalDetails principalDetails = new PrincipalDetails(member, null);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}

