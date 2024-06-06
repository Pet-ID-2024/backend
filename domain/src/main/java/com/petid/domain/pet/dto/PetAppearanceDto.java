package com.petid.domain.pet.dto;

import com.petid.domain.type.Breed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetAppearanceDto {

  private Breed breed;
  private String hairColor;
  private Integer weight;
  private String hairLength;

  public PetAppearanceDto(Breed breed, String hairColor, Integer weight, String hairLength) {
    this.breed = breed;
    this.hairColor = hairColor;
    this.weight = weight;
    this.hairLength = hairLength;
  }

  

}