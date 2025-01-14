package com.petid.api.member;

import com.petid.api.common.RequestUtil;
import com.petid.api.member.dto.MemberAuthDto;
import com.petid.api.member.dto.MemberInfoDto;
import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.model.MemberPolicy;
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
    private final MemberAuthManager memberAuthManager;

    @GetMapping
    public ResponseEntity<MemberInfoDto.Response> getMemberInfo(
            HttpServletRequest request
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        MemberAuthInfo memberAuthInfo = memberAuthManager.getByMemberId(memberId);

        return ResponseEntity.ok(MemberInfoDto.Response.from(memberAuthInfo));
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

        MemberAuthInfo memberAuthInfo = memberService.updateMemberAuth(authRequest.toDomain(memberId));

        return ResponseEntity.ok(MemberAuthDto.Response.from(memberAuthInfo));
    }

    @PatchMapping("/policy")
    public ResponseEntity<Boolean> updateOptionalPolicy(
            HttpServletRequest request,
            @RequestParam("ad") boolean ad
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        MemberPolicy updatedPolicy = memberService.updateOptionalPolicy(memberId, ad);

        return ResponseEntity.ok(updatedPolicy.advertisement());
    }

    @PostMapping("/image")
    public ResponseEntity<String> updateMemberProfileImage(
            HttpServletRequest request,
            @RequestBody String filePath
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        MemberAuthInfo updatedAuthInfo = memberService.updateMemberProfileImage(memberId, filePath);

        return ResponseEntity.ok(updatedAuthInfo.image());
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdrawMember(
            HttpServletRequest request
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        memberService.withdraw(memberId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/restore")
    public ResponseEntity<Void> restoreMember(
            HttpServletRequest request
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        memberService.restore(memberId);

        return ResponseEntity.ok().build();
    }
}
