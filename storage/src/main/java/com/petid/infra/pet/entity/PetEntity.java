package com.petid.infra.pet.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.petid.domain.pet.entity.Pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class PetEntity {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pet_id")
  private Long id;

  private String petChipNumber;
  private String petName;
  private String petBirthDate;
  private Character petSex;
  private Character petNeuteredYn;
  private LocalDateTime petNeuteredDate;
  private String petAddr;

  @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL)
  private PetAppearanceEntity appearance;

  @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
  private List<PetImageEntity> images;
  
  public Pet toDomain() {
      return new Pet(
              id,
              petChipNumber,
              petName,
              petBirthDate,
              petSex,
              petNeuteredYn,
              petNeuteredDate,
              petAddr,
              appearance.toDomain(),
              images.stream()  // Stream the list of PetImageEntity
              .map(PetImageEntity::toDomain) // Convert each entity to domain object
              .collect(Collectors.toList())  // Collect the converted objects into a list
      );
  }
  
  public static PetEntity from(Pet pet) {
	    return new PetEntity(
        		  pet.id(),
                  pet.petChipNumber(),
                  pet.petName(),
                  pet.petBirthDate(),
                  pet.petSex(),
                  pet.petNeuteredYn(),
                  pet.petNeuteredDate(),
                  pet.petAddr(),
                  PetAppearanceEntity.from(pet.appearance()),
                  pet.images().stream()
                  .map(PetImageEntity::from)  
                  .toList()
        );
    }
}
