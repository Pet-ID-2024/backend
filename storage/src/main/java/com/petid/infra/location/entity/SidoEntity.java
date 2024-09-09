package com.petid.infra.location.entity;

import com.petid.domain.location.model.Sido;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "sido")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String showName;

    public Sido toDomain() {
        return new Sido(
                id,
                name,
                showName
        );
    }
}
