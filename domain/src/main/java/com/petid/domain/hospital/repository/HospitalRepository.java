package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository {
    Optional<Hospital> findById(Long id);

    void saveAllBulk(List<Hospital> hospitals);

    List<Hospital> findAllByLocationIds(long sidoId, long sigunguId, List<Long> eupmundongIds);

    List<Hospital> findAllByLocationIdsOrderByLocation(int sidoId, int sigunguId, List<Long> eupmundongIds, double x, double y);
}
