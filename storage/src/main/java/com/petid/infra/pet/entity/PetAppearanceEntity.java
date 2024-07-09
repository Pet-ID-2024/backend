package com.petid.infra.pet.entity;

import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.type.Breed;
import com.petid.infra.common.BaseEntity;
import com.petid.infra.pet.converter.BreedConverter;

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
@Table(name = "pet_appearance")
public class PetAppearanceEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appearance_id")
  private Long id;

  @Column(name = "pet_id")
  private Long petId;

  @Convert(converter = BreedConverter.class)
  private Breed breed;
  
  private String hairColor;
  private Integer weight;
  private String hairLength;

  public PetAppearance toDomain() {
      return new PetAppearance(
    		  id,
    		  petId,
    		  breed,
    		  hairColor,
    		  weight,
    		  hairLength
      );
 }
  
  public static PetAppearanceEntity from(PetAppearance petAppearance) {
	  return new PetAppearanceEntity(
			  petAppearance.appearanceId(),
			  petAppearance.petId(),
			  petAppearance.breed(),
			  petAppearance.hairColor(),
			  petAppearance.weight(),
			  petAppearance.hairLength()
    );
  }
}
