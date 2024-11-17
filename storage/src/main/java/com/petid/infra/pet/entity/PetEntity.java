package com.petid.infra.pet.entity;

import com.petid.domain.pet.model.Pet;
import com.petid.domain.type.Chip;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long ownerId;
    private String petRegNo;
    private String petName;
    private String petBirthDate;
    private Character petSex;
    private Character petNeuteredYn;
    private String petNeuteredDate;
    private String petAddr;
    @Enumerated(EnumType.STRING)
    private Chip chipType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appearance_id", updatable = false)
    private PetAppearanceEntity appearance;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
    private List<PetImageEntity> petImages;

    private String signPath;

    public Pet toDomain() {
        return new Pet(
                id,
                ownerId,
                petRegNo,
                petName,
                petBirthDate,
                petSex,
                petNeuteredYn,
                petNeuteredDate,
                petAddr,
                chipType,
                (id != null && appearance != null) ? appearance.toDomain() : null,
                (id != null && petImages != null) ? petImages.stream()
                        .map(PetImageEntity::toDomain)
                        .toList() : null,
                signPath
        );
    }

    public static PetEntity from(Pet pet) {
        return new PetEntity(
                pet.petId(),
                pet.ownerId(),
                pet.petRegNo(),
                pet.petName(),
                pet.petBirthDate(),
                pet.petSex(),
                pet.petNeuteredYn(),
                pet.petNeuteredDate(),
                pet.petAddr(),
                pet.chipType(),
                pet.appearance() != null ? PetAppearanceEntity.from(pet.appearance()) : null,
                pet.petId() != null && pet.petImages() != null ? pet.petImages().stream()
                        .map(PetImageEntity::from)
                        .toList() : null,
                pet.signPath()
        );
    }
}
