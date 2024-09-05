package com.petid.domain.hospital.model;

import com.petid.domain.hospital.type.DayType;

import java.time.LocalTime;

public record HospitalHour(
        Long id,
        Long hospitalId,
        DayType day,
        LocalTime openingTime,
        LocalTime closingTime,
        boolean isClosed
) {
}
