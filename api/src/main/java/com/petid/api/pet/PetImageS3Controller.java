package com.petid.api.pet;

import com.petid.domain.pet.manager.PetManager;
import com.petid.domain.pet.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetImageS3Controller {

    private final PetManager petManager;
    private final S3Service S3service;

    @GetMapping("/{petId}/images/presigned-url")
    public ResponseEntity<String> getPetImageBucketUrl(@PathVariable(name = "petId") long petId, @RequestParam String filePath) {
        if (!petManager.existByPetId(petId)) return new ResponseEntity<>("pet not found", HttpStatus.NOT_FOUND);
        String url = S3service.createPresignedGetUrl(filePath);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/{petId}/images/presigned-url")
    public ResponseEntity<String> putPetImageBucketUrl(@PathVariable(name = "petId") long petId, @RequestBody String filePath) {
        if (!petManager.existByPetId(petId)) return new ResponseEntity<>("pet not found", HttpStatus.NOT_FOUND);
        String url = S3service.createPresignedPutUrl(filePath);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/sign/images/presigned-url")
    public ResponseEntity<String> putPetIdSignBucketUrl(
            @RequestBody String filePath
    ) {
        String url = S3service.createPresignedPutUrl(filePath);
        return ResponseEntity.ok(url);
    }

}
