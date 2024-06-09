package com.petid.api.pet;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.service.PetService;

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

  @PostMapping
  public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
      Pet createdPet = petService.createPet(pet);
      return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
  }

  @PutMapping("/{petId}/appearance")
  public ResponseEntity<PetAppearance> updatePetAppearance(@PathVariable Long petId, @RequestBody PetAppearance petAppearance) {
     PetAppearance updatedAppearance = petService.updatePetAppearance(petId, petAppearance);
      return new ResponseEntity<>(updatedAppearance, HttpStatus.OK);
  }

  @PostMapping("/{petId}/images")
  public ResponseEntity<PetImage> addPetImage(@PathVariable Long petId, @RequestBody PetImage petImage) {
      PetImage createdImage = petService.createPetImage(petId, petImage);
      return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
  }

  @GetMapping("/{petId}")
  public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
      Optional<Pet> pet = petService.findPetById(petId);
      return pet.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity<List<Pet>> getAllPets() {
      List<Pet> pets = petService.findAllPets();
      return new ResponseEntity<>(pets, HttpStatus.OK);
  }
}
