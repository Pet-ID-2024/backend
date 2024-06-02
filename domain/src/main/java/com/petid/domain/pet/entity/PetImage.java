package com.petid.domain.pet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet_image")
public class PetImage  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_image_id")
    private Long id;
  
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
  
    private String imagePath;
}
