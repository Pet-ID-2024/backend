package com.petid.api.member;

import com.petid.api.common.RequestUtil;
import com.petid.api.member.dto.MemberAuthDto;
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

    @GetMapping("/auth")
    public ResponseEntity<Boolean> isMemberAuth(
            HttpServletRequest request
    ) {
        String uid = RequestUtil.getUidFromRequest(request);
        boolean isMemberAuthed = memberService.isMemberAuthed(uid);

        return ResponseEntity.ok(isMemberAuthed);
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberAuthDto.Response> saveMemberAuth(
            HttpServletRequest request,
            @RequestBody MemberAuthDto.Request authRequest
    ) {
        String uid = RequestUtil.getUidFromRequest(request);

        MemberAuth memberAuth = memberService.saveMemberAuth(authRequest.toDomain(uid));

        return ResponseEntity.ok(MemberAuthDto.Response.from(memberAuth));
    }

    @PatchMapping("/policy")
    public ResponseEntity<Boolean> updateOptionalPolicy(
            HttpServletRequest request,
            @RequestParam("ad") boolean ad
    ) {
        String uid = RequestUtil.getUidFromRequest(request);

        memberService.updateOptionalPolicy(uid, ad);

        return ResponseEntity.ok(ad);
    }
}
