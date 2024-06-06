package com.petid.domain.exception;

public class PetNotFoundException extends PetDataNotFoundException {
  
    private Long petId;
  
    public PetNotFoundException(Long petId) {
      super("Pet with ID " + petId + " not found");
      this.petId = petId;
    }
  
    public Long getPetId() {
      return petId;
    }
  }
