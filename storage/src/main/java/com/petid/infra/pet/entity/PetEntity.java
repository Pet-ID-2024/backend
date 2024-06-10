package com.petid.infra.pet.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.petid.domain.pet.model.Pet;
import com.petid.infra.common.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class PetEntity extends BaseEntity {
    
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

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
  private PetAppearanceEntity appearance;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
  private List<PetImageEntity> petImages;
  
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
              id != null && appearance != null ? appearance.toDomain() : null,
              id != null && petImages != null ? petImages.stream()  // Stream the list of PetImageEntity
              .map(PetImageEntity::toDomain) // Convert each entity to domain object
              .collect(Collectors.toList())  : null // Collect the converted objects into a list
      );
  }
  
  public static PetEntity from(Pet pet) {
	    return new PetEntity(
        		  pet.petId(),
                  pet.petChipNumber(),
                  pet.petName(),
                  pet.petBirthDate(),
                  pet.petSex(),
                  pet.petNeuteredYn(),
                  pet.petNeuteredDate(),
                  pet.petAddr(),
                  pet.petId() != null ? PetAppearanceEntity.from(pet.appearance()) : null,
                  pet.petId() != null ? pet.petImages().stream()
                  .map(PetImageEntity::from)  
                  .toList() : null
        );
    }
}
