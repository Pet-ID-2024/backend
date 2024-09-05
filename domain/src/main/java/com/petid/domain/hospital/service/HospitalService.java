package com.petid.domain.hospital.service;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public List<Hospital> findAllHospital(
            int sidoId,
            int sigunguId,
            List<Long> eupmundongIds
    ) {
        return hospitalRepository.findAllByLocationIds(sidoId, sigunguId, eupmundongIds);
    }

    public List<Hospital> findAllHospitalOrderByLocation(
            int sidoId,
            int sigunguId,
            List<Long> eupmundongIds,
            double lat,
            double lon
    ) {
        return hospitalRepository.findAllByLocationIdsOrderByLocation(sidoId, sigunguId, eupmundongIds, lat, lon);
    }
}
