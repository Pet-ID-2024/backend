package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.type.DayType;
import com.petid.infra.hospital.entity.HospitalHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalHourJpaRepository extends JpaRepository<HospitalHourEntity, Long> {
    Optional<HospitalHourEntity> findByHospitalIdAndDay(long hospitalId, DayType day);
}
