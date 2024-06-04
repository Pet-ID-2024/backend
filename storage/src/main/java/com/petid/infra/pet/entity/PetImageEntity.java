package com.petid.infra.pet.entity;

import com.petid.domain.pet.entity.PetImage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet_image")
public class PetImageEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_image_id")
    private Long id;
  
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
  
    private String imagePath;
    
    public static PetImageEntity  from(PetImage petImage) {
        return new PetImageEntity(
        		petImage.id(),
                PetEntity.from(petImage.pet()),
                petImage.imagePath()                
        );
    }
    
    public PetImage  toDomain() {
        return new PetImage(
        		id,
        		pet.toDomain(),
        		imagePath
        		);
    }
    
    
}
