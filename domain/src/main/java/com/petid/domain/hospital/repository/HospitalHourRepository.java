package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.HospitalHour;
import com.petid.domain.hospital.type.DayType;

import java.util.Optional;

public interface HospitalHourRepository {
    Optional<HospitalHour> findByHospitalIdAndDay(long hospitalId, DayType day);
}
