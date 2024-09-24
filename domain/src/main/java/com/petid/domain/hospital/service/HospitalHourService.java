package com.petid.domain.hospital.service;

import com.petid.domain.hospital.manager.HospitalHourManager;
import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalHourService {


    private final HospitalHourManager hospitalHourManager;

    public List<LocalTime> findAvailableTimes(
            long hospitalId,
            DayType day
    ) {
        HospitalHour hourData = hospitalHourManager.getByHospitalIdAndDay(hospitalId, day);

        List<LocalTime> availableTimes = new ArrayList<>();

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
