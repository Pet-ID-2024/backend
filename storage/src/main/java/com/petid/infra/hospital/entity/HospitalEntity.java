package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.model.HospitalLocation;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.List;

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
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imageUrl;
    private String address;
    private String name;
    private String hours;
    private String tel;
    private String vet;
    private Point location;

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
                vet,
                HospitalLocation.from(location.getX(), location.getY())
        );
    }
}
