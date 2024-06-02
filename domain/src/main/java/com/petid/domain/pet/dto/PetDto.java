package com.petid.domain.pet.dto;

import com.petid.domain.pet.entity.Pet;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PetDto {

    private Long id;
    private String petChipNumber;
    private String petName;
    private String petBirthDate;
    private String petSex;
    private Character petNeuteredYn;
    private LocalDateTime petNeuteredDate;
    private String petAddr;
    private String createdAt;
  
    private PetAppearanceDto appearance;
    private PetImageDto image;
  
    
    public Pet toPetEntity() {
      Pet pet = new Pet();
      pet.setId(this.id);
      pet.setPetChipNumber(this.petChipNumber);
      pet.setPetName(this.petName);
      pet.setPetBirthDate(this.petBirthDate);
      pet.setPetSex(this.petSex);
      pet.setPetNeuteredYn(this.petNeuteredYn);
      pet.setPetNeuteredDate(this.petNeuteredDate);
      pet.setPetAddr(this.petAddr);
      
      return pet;
    }
  }