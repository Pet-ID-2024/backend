package com.petid.api.member;

import com.petid.api.member.dto.MemberAuthRequest;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
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

    private final MemberManager memberManager;
    private final MemberService memberService;

    @GetMapping("/auth")
    public ResponseEntity<Boolean> isMemberAuth(
            HttpServletRequest request
    ) {
        String uid = request.getAttribute("uid").toString();
        boolean isMemberAuthed = memberService.isMemberAuthed(uid);

        return ResponseEntity.ok(isMemberAuthed);
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberAuth> saveMemberAuth(
            HttpServletRequest request,
            @RequestBody MemberAuthRequest authRequest
    ) {
        String uid = request.getAttribute("uid").toString();
        Member member = memberManager.getByUid(uid);

        MemberAuth saved = memberService.saveMemberAuth(member, authRequest.toDomain(member.id()));

        return ResponseEntity.ok(saved);
    }
}
