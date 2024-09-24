package com.petid.domain.hospital.service;

import com.petid.domain.hospital.manager.HospitalHourManager;
import com.petid.domain.hospital.manager.HospitalOrderManager;
import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalHourService {

    private final HospitalHourManager hospitalHourManager;
    private final HospitalOrderManager hospitalOrderManager;

    public List<String> findAvailableTimes(
            long hospitalId,
            DayType day,
            LocalDate date
    ) {
        HospitalHour hourData = hospitalHourManager.getByHospitalIdAndDay(hospitalId, day);

        Set<LocalTime> availableTimes = hospitalHourManager.getAvailableTimes(hourData);
        Set<LocalTime> unavailableTimes = hospitalOrderManager.getUnavailableTimes(hospitalId, date);

        availableTimes.removeAll(unavailableTimes);

        return availableTimes
                .stream()
                .map(LocalTime::toString)
                .toList();
    }
}
