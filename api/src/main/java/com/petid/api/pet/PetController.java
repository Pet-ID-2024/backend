package com.petid.api.pet;

import com.petid.api.common.RequestUtil;
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
import java.util.Optional;



@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetController {

  private final PetService petService;
  
  private final S3Service S3service;  

  @PostMapping
  public ResponseEntity<Pet> createPet(
          HttpServletRequest request,
          @RequestBody Pet pet
  ) {
      long memberId = RequestUtil.getMemberIdFromRequest(request);
      Pet targetPet = pet.setOwnerId(memberId);

      Pet createdPet = petService.createPet(targetPet);
      return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
  }

  @PutMapping("/{petId}")
  public ResponseEntity<Pet> updatePet(@PathVariable(name = "petId") Long petId, @RequestBody Pet pet) {
	 if (!petId.equals(pet.petId())) {
          return ResponseEntity.badRequest().build(); // 400 Bad Request if IDs do not match
     }
    Pet updatedPet = petService.updatePet(petId, pet);
    return new ResponseEntity<>(updatedPet, HttpStatus.OK);
  }

  @PutMapping("/{petId}/appearance")
  public ResponseEntity<PetAppearance> updatePetAppearance(@PathVariable(name = "petId") Long petId, @RequestBody PetAppearance petAppearance) {
     PetAppearance updatedAppearance = petService.updatePetAppearance(petId, petAppearance);
      return new ResponseEntity<>(updatedAppearance, HttpStatus.OK);
  }

  @PostMapping("/{petId}/images")
  public ResponseEntity<PetImage> addPetImage(@PathVariable(name = "petId") Long petId, @RequestBody PetImage petImage) {
      PetImage createdImage = petService.createPetImage(petId, petImage);
      return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
  }

  @GetMapping("/{petId}")
  public ResponseEntity<Pet> getPetById(@PathVariable (name = "petId") Long petId) {
      Optional<Pet> pet = petService.findPetById(petId);
      return pet.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity<List<Pet>> getAllPets() {
      List<Pet> pets = petService.findAllPets();
      return new ResponseEntity<>(pets, HttpStatus.OK);
  }
  
  @DeleteMapping("/{petId}")
  public ResponseEntity<Void> deletePet(@PathVariable(name = "petId") Long petId) {
      petService.deletePetById(petId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{petId}/images/{imageId}")
  public ResponseEntity<Void> deletePetImage(@PathVariable(name = "petId") Long petId, @PathVariable(name = "imageId") Long imageId) {
	  if(!petService.findPetById(petId).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
      petService.deletePetImage(petId, imageId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @GetMapping("/{petId}/images/presigned-url")
  public ResponseEntity<String> getPetImageBucketUrl(@PathVariable (name = "petId") Long petId, @RequestParam String filePath) {
	  if(!petService.findPetById(petId).isPresent()) return new ResponseEntity<String>("pet not found", HttpStatus.OK);;
	  String url = S3service.createPresignedGetUrl(filePath);
      return new ResponseEntity<String>(url, HttpStatus.OK);
  }

  @PostMapping("/{petId}/images/presigned-url")
  public ResponseEntity<String> putPetImageBucketUrl(@PathVariable (name = "petId") Long petId, @RequestBody String filePath) {
	  if(!petService.findPetById(petId).isPresent()) return new ResponseEntity<String>("pet not found",HttpStatus.NOT_FOUND);;
	  String url = S3service.createPresignedPutUrl(filePath);
      return new ResponseEntity<String>(url, HttpStatus.OK);
  }
  
}
