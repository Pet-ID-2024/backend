package com.petid.infra.pet.entity;

import com.petid.domain.pet.model.PetAppearance;
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
@Table(name = "pet_appearance")
public class PetAppearanceEntity extends BaseEntity {
	
 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appearance_id")
  private Long id;

  private String breed;
  
  private String hairColor;
  private Double weight;
  private String hairLength;

  public PetAppearance toDomain() {
      return new PetAppearance(
    		  id,    		  
    		  breed,
    		  hairColor,
    		  weight,
    		  hairLength
      );
 }
  
  public static PetAppearanceEntity from(PetAppearance petAppearance) {
	  return new PetAppearanceEntity(
			  petAppearance.appearanceId(),			  
			  petAppearance.breed(),
			  petAppearance.hairColor(),
			  petAppearance.weight(),
			  petAppearance.hairLength()
    );
  }
  
  

}
