package com.petid.api.member;

import com.petid.api.common.RequestUtil;
import com.petid.api.member.dto.MemberAuthDto;
import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberManager memberManager;
    private final MemberAuthManager memberAuthManager;

    @GetMapping
    public ResponseEntity<MemberAuthDto.Response> getMemberInfo(
            HttpServletRequest request
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        MemberAuth memberAuth = memberAuthManager.getByMemberId(memberId);

        return ResponseEntity.ok(MemberAuthDto.Response.from(memberAuth));
    }

    @GetMapping("/auth")
    public ResponseEntity<Boolean> isMemberAuth(
            HttpServletRequest request
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        boolean isMemberAuthed = memberService.isMemberAuthed(memberId);

        return ResponseEntity.ok(isMemberAuthed);
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberAuthDto.Response> updateMemberAuth(
            HttpServletRequest request,
            @RequestBody MemberAuthDto.Request authRequest
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        MemberAuth memberAuth = memberService.updateMemberAuth(authRequest.toDomain(memberId));

        return ResponseEntity.ok(MemberAuthDto.Response.from(memberAuth));
    }

    @PatchMapping("/policy")
    public ResponseEntity<Boolean> updateOptionalPolicy(
            HttpServletRequest request,
            @RequestParam("ad") boolean ad
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        memberService.updateOptionalPolicy(memberId, ad);

        return ResponseEntity.ok(ad);
    }
}
