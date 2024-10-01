package com.petid.domain.hospital.service;

import com.petid.domain.hospital.manager.HospitalHourManager;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalHourManager hospitalHourManager;

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

    public boolean isOffDay(
            long hospitalId,
            DayType day
    ) {
        HospitalHour hospitalHour = hospitalHourManager.getByHospitalIdAndDay(hospitalId, day);

        return hospitalHour.isClosed();
    }
}
