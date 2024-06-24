package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository {
    Optional<Hospital> findById(Long id);

    void saveAllBulk(List<Hospital> hospitals);

    List<Hospital> findAllBySigunguId(int sidoId, int sigunguId, List<Integer> eupmundongIds);
}
