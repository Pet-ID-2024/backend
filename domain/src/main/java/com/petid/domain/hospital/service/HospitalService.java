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
            List<Integer> eupmundongIds
    ) {
        return hospitalRepository.findAllBySigunguId(sidoId, sigunguId, eupmundongIds);
    }
}
