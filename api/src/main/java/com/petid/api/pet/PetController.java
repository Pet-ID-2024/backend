package com.petid.api.pet;

import com.petid.api.common.RequestUtil;
import com.petid.api.pet.dto.*;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetController {

    private final PetService petService;

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

    @PostMapping("/{petId}/images/{imageId}")
    public ResponseEntity<PetImageDto.Response> addPetImage(
            @PathVariable(name = "petId") long petId,
            @PathVariable(name = "imageId") long imageId,
            @RequestBody PetImageDto.Request request
    ) {
        PetImage createdImage = petService.createPetImage(request.toDomain(imageId, petId));

        return new ResponseEntity<>(
                PetImageDto.Response.from(createdImage),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{petId}/images/{imageId}")
    public ResponseEntity<PetImageDto.Response> updatePetImage(
            @PathVariable(name = "petId") long petId,
            @PathVariable(name = "imageId") long imageId,
            @RequestBody PetImageDto.Request request
    ) {
        PetImage createdImage = petService.updatePetImage(request.toDomain(imageId, petId));

        return new ResponseEntity<>(
                PetImageDto.Response.from(createdImage),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto.Response> getPetById(
            @PathVariable(name = "petId") long petId
    ) {
        Pet pet = petService.findPetById(petId);

        return ResponseEntity.ok(
                PetDto.Response.from(pet)
        );
    }

    @DeleteMapping("/{petId}/images/{imageId}")
    public ResponseEntity<Void> deletePetImage(
            @PathVariable(name = "petId") long petId,
            @PathVariable(name = "imageId") long imageId
    ) {
        petService.deletePetImage(petId, imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
