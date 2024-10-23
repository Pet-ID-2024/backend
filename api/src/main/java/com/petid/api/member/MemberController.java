package com.petid.api.member;

import com.petid.api.common.RequestUtil;
import com.petid.api.member.dto.MemberAuthDto;
import com.petid.domain.member.manager.MemberAuthManager;
import com.petid.domain.member.model.MemberAuth;
import com.petid.domain.member.service.MemberService;
import com.petid.domain.pet.service.S3Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final S3Service s3Service;
    private final MemberService memberService;
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

    @GetMapping("/images/presigned-url")
    public ResponseEntity<String> getPetImageBucketUrl(
            @RequestParam String filePath
    ) {
        String url = s3Service.createPresignedGetUrl(filePath);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/images/presigned-url")
    public ResponseEntity<String> putPetImageBucketUrl(
            HttpServletRequest request,
            @RequestBody String filePath
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        memberService.updateMemberProfileImage(memberId, filePath);

        String url = s3Service.createPresignedPutUrl(filePath);
        return ResponseEntity.ok(url);
    }
}
