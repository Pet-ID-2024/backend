package com.petid.infra.pet.entity;

import com.petid.domain.pet.model.PetImage;
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
@Table(name = "pet_image")
public class PetImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_image_id")
    private Long id;
  
    @Column(name = "pet_id")
    private Long petId;
  
    private String imagePath;
    
    public static PetImageEntity  from(PetImage petImage) {
        return new PetImageEntity(
        		petImage.petImageId(),
                petImage.petId(),
                petImage.imagePath()                
        );
    }
    
    public PetImage  toDomain() {
        return new PetImage(
        		id,
        		petId,
        		imagePath
        		);
    }
    
    
}
