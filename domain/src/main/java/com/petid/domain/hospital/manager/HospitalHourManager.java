package com.petid.domain.hospital.manager;

import com.petid.domain.exception.HospitalHourNotFoundException;
import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.repository.HospitalHourRepository;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

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

    public Set<LocalTime> getAvailableTimes(
            HospitalHour hourData
    ) {
        Set<LocalTime> availableTimes = new TreeSet<>();

        LocalTime currentTime = hourData.openingTime();
        LocalTime closingTime = hourData.closingTime();
        LocalTime breakingTime = hourData.breakingTime();
        LocalTime breakEndTime = hourData.breakingTime().plusMinutes(hourData.breakingUnit());
        int orderInterval = 30;

        while (!currentTime.isAfter(closingTime)) {
            if (currentTime.isAfter(breakingTime) && currentTime.isBefore(breakEndTime)) {
                currentTime = breakEndTime;
            } else {
                availableTimes.add(currentTime);
                currentTime = currentTime.plusMinutes(orderInterval);
            }
        }

        return availableTimes;
    }
}
