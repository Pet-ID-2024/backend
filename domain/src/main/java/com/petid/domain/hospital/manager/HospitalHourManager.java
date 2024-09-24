package com.petid.domain.hospital.manager;

import com.petid.domain.exception.HospitalHourNotFoundException;
import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.repository.HospitalHourRepository;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalHourManager {

    private final HospitalHourRepository hospitalHourRepository;

    public HospitalHour getByHospitalIdAndDay(
            long hospitalId,
            DayType day
    ) {
        return hospitalHourRepository.findByHospitalIdAndDay(hospitalId, day)
                .orElseThrow(() -> new HospitalHourNotFoundException(hospitalId, day));
    }
}
