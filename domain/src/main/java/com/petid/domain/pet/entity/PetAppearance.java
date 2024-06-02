package com.petid.domain.pet.entity;


import com.petid.domain.pet.dto.PetAppearanceDto;
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
public class PetAppearance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appearance_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "pet_id")
  private Pet pet;

  private Breed breed;
  private String hairColor;
  private Integer weight;
  private String hairLength;

  public PetAppearanceDto toDto() {
        return new PetAppearanceDto(
               //
        );
    }

}
