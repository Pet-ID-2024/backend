package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.Hospital;

import java.util.Optional;

public interface HospitalRepository {
    Optional<Hospital> findById(Long id);
}
