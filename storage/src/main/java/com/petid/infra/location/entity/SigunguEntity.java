package com.petid.infra.location.entity;

import com.petid.domain.location.model.Sigungu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "sigungu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SigunguEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sido_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SidoEntity sido;

    private String name;

    public Sigungu toDomain() {
        return new Sigungu(
                id,
                sido.getId(),
                name
        );
    }
}
