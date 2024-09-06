package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.type.DayType;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "hospital_hour")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalHourEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hospitalId;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayType day;

    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalTime breakingTime;
    private Integer breakingUnit;
    private boolean isClosed;

    public static HospitalHourEntity from(
            HospitalHour hospitalHour
    ) {
        return new HospitalHourEntity(
                hospitalHour.id(),
                hospitalHour.hospitalId(),
                hospitalHour.day(),
                hospitalHour.openingTime(),
                hospitalHour.closingTime(),
                hospitalHour.breakingTime(),
                hospitalHour.breakingUnit(),
                hospitalHour.isClosed()
        );
    }

    public HospitalHour toDomain() {
        return new HospitalHour(
                id,
                hospitalId,
                day,
                openingTime,
                closingTime,
                breakingTime,
                breakingUnit,
                isClosed
        );
    }
}
