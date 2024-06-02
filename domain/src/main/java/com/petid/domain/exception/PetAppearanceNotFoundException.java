package com.petid.domain.exception;

  
public class PetAppearanceNotFoundException extends PetDataNotFoundException {
  
    private Long petId;
  
    public PetAppearanceNotFoundException(Long petId) {
      super("Pet appearance for pet with ID " + petId + " not found");
      this.petId = petId;
    }
  
    public Long getPetId() {
      return petId;
    }
  }