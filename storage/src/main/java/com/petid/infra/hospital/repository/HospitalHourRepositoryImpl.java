package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.repository.HospitalHourRepository;
import com.petid.domain.hospital.type.DayType;
import com.petid.infra.hospital.entity.HospitalHourEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HospitalHourRepositoryImpl implements HospitalHourRepository {

    private final HospitalHourJpaRepository jpaRepository;

    @Override
    public Optional<HospitalHour> findByHospitalIdAndDay(
            long hospitalId,
            DayType day
    ) {
        return jpaRepository.findByHospitalIdAndDay(hospitalId, day)
                .map(HospitalHourEntity::toDomain);
    }
}
