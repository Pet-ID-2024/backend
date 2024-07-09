package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.Hospital;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "hospital")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private long sidoId;
    private long sigunguId;
    private long eupmundongId;
    private String imageUrl;
    private String address;
    private String name;
    private String hours;
    private String tel;
    private String vet;

    public static HospitalEntity from(
            Hospital hospital
    ) {
        return new HospitalEntity(
                hospital.id(),
                hospital.sidoId(),
                hospital.sigunguId(),
                hospital.eupmundongId(),
                hospital.imageUrl(),
                hospital.address(),
                hospital.name(),
                hospital.hours(),
                hospital.tel(),
                hospital.vet()
        );
    }

    public Hospital toDomain() {
        return new Hospital(
                id,
                sidoId,
                sigunguId,
                eupmundongId,
                imageUrl,
                address,
                name,
                hours,
                tel,
                vet
        );
    }
}
