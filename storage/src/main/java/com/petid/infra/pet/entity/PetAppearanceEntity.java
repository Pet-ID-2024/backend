package com.petid.infra.pet.entity;

import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.type.Breed;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet_appearance")
public class PetAppearanceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appearance_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "pet_id")
  private PetEntity pet;

  private Breed breed;
  private String hairColor;
  private Integer weight;
  private String hairLength;

  public PetAppearance toDomain() {
      return new PetAppearance(
    		  id,
    		  pet.toDomain(),
    		  breed,
    		  hairColor,
    		  weight,
    		  hairLength
      );
 }
  
  public static PetAppearanceEntity from(PetAppearance petAppearance) {
	  return new PetAppearanceEntity(
			  petAppearance.id(),
			  PetEntity.from(petAppearance.pet()),
			  petAppearance.breed(),
			  petAppearance.hairColor(),
			  petAppearance.weight(),
			  petAppearance.hairLength()
    );
  }
}
