package com.petid.domain.pet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetImageDto {

  private String imagePath;

  public PetImageDto(String imagePath) {
    this.imagePath = imagePath;
  }
}