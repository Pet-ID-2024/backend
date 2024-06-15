package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.infra.hospital.entity.HospitalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HospitalRepositoryImpl implements HospitalRepository {

    private final HospitalJpaRepository jpaRepository;

    @Override
    public Optional<Hospital> findById(Long id) {
        return jpaRepository.findById(id)
                .map(HospitalEntity::toDomain);
    }
}
