package com.petid.api.member;

import com.petid.domain.pet.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberImageS3Controller {

    private final S3Service s3Service;

    @GetMapping("/images/presigned-url")
    public ResponseEntity<String> getMemberImageBucketUrl(
            @RequestParam String filePath
    ) {
        String url = s3Service.createPresignedGetUrl(filePath);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/images/presigned-url")
    public ResponseEntity<String> putMemberImageBucketUrl(
            @RequestBody String filePath
    ) {
        String url = s3Service.createPresignedPutUrl(filePath);
        return ResponseEntity.ok(url);
    }
}
