package com.petid.api.pet;

import java.util.List;
import java.util.Optional;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.service.PetService;

import lombok.RequiredArgsConstructor;

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
    Optional<Pet> petOptional = petService.getPetById(pet.id());
    Pet createdPet = petService.createPet(petOptional.get());
    //PetAppearance createdAppearance = petService.createAppearance(createdPet.getId(), petDto.getAppearance());
    //PetImage createdImage = petService.createImage(createdPet.getId(), createdAppearance.toDto());
    //return ResponseEntity.ok(new PetDto(createdPet, createdAppearance, createdImage));    
    return null;
  }

  @GetMapping
  public ResponseEntity<List<Pet>> getAllPets() {
     List<Pet> pets = petService.getAllPets();
    /*List<PetDto> petDtos = pets.stream()
        .map(pet -> new PetDto(pet, petService.getPetById(pet.getId()), petService.getPetById(pet.getId())))
        .collect(Collectors.toList());
    return ResponseEntity.ok(pets);
    */
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
    Optional<Pet> petOptional = petService.getPetById(id);
    if (petOptional.isPresent()) {
      return ResponseEntity.ok(petOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
    Optional<Pet> petOptional = petService.getPetById(id);
    if (petOptional.isPresent()) {
      Pet existingPet = petOptional.get();
      
      //

      //petService.updatePet(existingPet);
      return ResponseEntity.ok(existingPet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePet(@PathVariable Long id) {
    petService.deletePet(id);
    return ResponseEntity.noContent().build();
  }

   @PutMapping("/{petId}/appearance")
  public ResponseEntity<PetAppearance> updatePetAppearance(@PathVariable Long petId, @RequestBody PetAppearance petAppearance) {
    Optional<Pet> petOptional = petService.getPetById(petId);
    
    PetAppearance updatedAppearance = petService.updatePetAppearance(petId, null, petAppearance);    

    return ResponseEntity.ok(updatedAppearance);
  }

  @PostMapping("/{petId}/image")
  public ResponseEntity<PetImage> createPetImage(@PathVariable Long petId, @RequestBody PetImage petImage) {
    PetImage createdImage = petService.createImage(petId, petImage);
    return ResponseEntity.ok(createdImage);
  }

  // Update Pet Image (PUT)
  @PutMapping("/{petId}/image")
  public ResponseEntity<PetImage> updatePetImage(@PathVariable Long petId, @RequestBody PetImage petImage) {
    PetImage updatedImage = petService.createImage(petId, petImage);
    return ResponseEntity.ok(updatedImage);
  }
}
