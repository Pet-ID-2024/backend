package com.petid.domain.exception;

public class PetAlreadyExistsException extends PetDataException {
    public PetAlreadyExistsException(Long memberId) {
      super("Member ID " + memberId + " Pet data already exists");
    }
  }
