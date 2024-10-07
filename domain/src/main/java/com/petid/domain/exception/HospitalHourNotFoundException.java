package com.petid.domain.exception;

import com.petid.domain.hospital.type.DayType;

public class HospitalHourNotFoundException extends HospitalDataNotFoundException {

    public HospitalHourNotFoundException(Long id, DayType day) {
        super("Hospital ID : " + id + " and Day : " + day.name() + " not found");
    }
}
