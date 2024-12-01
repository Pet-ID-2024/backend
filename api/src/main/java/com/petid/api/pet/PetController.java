package com.petid.api.pet;

import com.petid.api.common.RequestUtil;
import com.petid.api.pet.dto.*;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.service.PetService;
import com.petid.domain.pet.service.S3Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetController {

    private final PetService petService;

    private final S3Service S3service;

    @PostMapping
    public ResponseEntity<PetDto.Response> createPetId(
            HttpServletRequest request,
            @RequestBody PetIdDto.Request requestBody
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);
        Pet targetPet = requestBody.toPetDomain().setOwnerId(memberId);
        MemberAuthInfo memberAuthInfo = requestBody.proposer().toDomain(memberId);

        Pet createdPet = petService.createPet(targetPet, memberAuthInfo);

        return new ResponseEntity<>(
                PetDto.Response.from(createdPet),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetDto.Response> updatePet(
            @PathVariable(name = "petId") long petId,
            @RequestBody PetUpdateDto.Request request
    ) {
        Pet updateData = request.toDomain();
        Pet updatedPet = petService.updatePet(petId, updateData);

        return new ResponseEntity<>(
                PetDto.Response.from(updatedPet),
                HttpStatus.OK
        );
    }

    @PutMapping("/{petId}/appearance")
    public ResponseEntity<PetAppearanceDto.Response> updatePetAppearance(
            @PathVariable(name = "petId") long petId,
            @RequestBody PetAppearanceDto.Request petAppearance
    ) {
        PetAppearance updatedAppearance = petService.updatePetAppearance(petId, petAppearance.toDomain());

        return new ResponseEntity<>(
                PetAppearanceDto.Response.from(updatedAppearance),
                HttpStatus.OK
        );
    }

    @PostMapping("/{petId}/images")
    public ResponseEntity<PetImageDto.Response> addPetImage(
            @PathVariable(name = "petId") Long petId,
            @RequestBody PetImage petImage
    ) {
        PetImage createdImage = petService.createPetImage(petId, petImage);

        return new ResponseEntity<>(
                PetImageDto.Response.from(createdImage),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto.Response> getPetById(
            @PathVariable(name = "petId") Long petId
    ) {
        Pet pet = petService.findPetById(petId);

        return ResponseEntity.ok(
                PetDto.Response.from(pet)
        );
    }

    @GetMapping
    public ResponseEntity<List<PetDto.Response>> getAllPets() {
        List<Pet> pets = petService.findAllPets();

        return ResponseEntity.ok(
                pets.stream().map(PetDto.Response::from).toList()
        );
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable(name = "petId") Long petId) {
        petService.deletePetById(petId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{petId}/images/{imageId}")
    public ResponseEntity<Void> deletePetImage(@PathVariable(name = "petId") Long petId, @PathVariable(name = "imageId") Long imageId) {
        if (!petService.existByPetId(petId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        petService.deletePetImage(petId, imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{petId}/images/presigned-url")
    public ResponseEntity<String> getPetImageBucketUrl(@PathVariable(name = "petId") Long petId, @RequestParam String filePath) {
        if (!petService.existByPetId(petId)) return new ResponseEntity<>("pet not found", HttpStatus.NOT_FOUND);
        String url = S3service.createPresignedGetUrl(filePath);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/{petId}/images/presigned-url")
    public ResponseEntity<String> putPetImageBucketUrl(@PathVariable(name = "petId") Long petId, @RequestBody String filePath) {
        if (!petService.existByPetId(petId)) return new ResponseEntity<>("pet not found", HttpStatus.NOT_FOUND);
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
