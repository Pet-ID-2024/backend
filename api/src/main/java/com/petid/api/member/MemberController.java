package com.petid.api.member;

import com.petid.api.dto.BasicResponse;
import com.petid.domain.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/auth")
    public BasicResponse<Boolean> auth(
            HttpServletRequest request
    ) {
        String uid = request.getAttribute("uid").toString();
        boolean isMemberAuthed = memberService.isMemberAuthed(uid);

        return BasicResponse.ok(isMemberAuthed);
    }
}
