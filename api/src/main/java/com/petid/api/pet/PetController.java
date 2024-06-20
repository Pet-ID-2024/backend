package com.petid.api.pet;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.service.PetService;
import com.petid.domain.pet.service.S3Service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetController {

  private final PetService petService;
  
  private final S3Service S3service;  

  @PostMapping
  public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
      Pet createdPet = petService.createPet(pet);
      return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
  }

  @PutMapping("/{petId}")
  public ResponseEntity<Pet> updatePet(@PathVariable(name = "petId") Long petId, @RequestBody Pet pet) {
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
      petService.deletePetImage(petId, imageId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @GetMapping("/{petId}/images/presigned-url")
  public ResponseEntity<String> getPetImageBucketUrl(@PathVariable (name = "petId") Long petId, @RequestBody String fileName) {
	  String url = S3service.createPresignedGetUrl(fileName);
      return new ResponseEntity<String>(url, HttpStatus.OK);
  }
  
}
