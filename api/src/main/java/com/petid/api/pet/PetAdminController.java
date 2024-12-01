package com.petid.api.pet;

import com.petid.api.pet.dto.PetDto;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetAdminController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDto.Response>> getAllPets() {
        List<Pet> pets = petService.findAllPets();

        return ResponseEntity.ok(
                pets.stream().map(PetDto.Response::from).toList()
        );
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable(name = "petId") long petId) {
        petService.deletePetById(petId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
