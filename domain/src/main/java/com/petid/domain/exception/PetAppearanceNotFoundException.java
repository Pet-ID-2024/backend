package com.petid.domain.exception;

  
public class PetAppearanceNotFoundException extends PetDataException {
    public PetAppearanceNotFoundException(long appearanceId) {
      super("Pet appearance for pet with ID " + appearanceId + " not found");
    }
  }