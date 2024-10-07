package com.petid.infra.location.entity;

import com.petid.domain.location.model.Eupmundong;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "eupmundong")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EupmundongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sigungu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SigunguEntity sigungu;

    private String name;
    private String showName;

    public Eupmundong toDomain() {
        return new Eupmundong(
                id,
                sigungu.getId(),
                name,
                showName
        );
    }
}
