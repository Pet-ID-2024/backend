package com.petid.domain.pet.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.petid.domain.pet.dto.PetDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pet_id")
  private Long id;

  private String petChipNumber;
  private String petName;
  private String petBirthDate;
  private String petSex;
  private Character petNeuteredYn;
  private LocalDateTime petNeuteredDate;
  private String petAddr;

  @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL)
  private PetAppearance appearance;

  @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
  private List<PetImage> images;
  
  public static Pet from(PetDto petDto) {
        return new Pet(
            //petDto.getPetName();      
        );
    }
}
