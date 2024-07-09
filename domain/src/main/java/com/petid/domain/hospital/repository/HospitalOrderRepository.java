package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.HospitalOrder;

import java.util.Optional;

public interface HospitalOrderRepository {
    HospitalOrder save(HospitalOrder hospitalOrder);

    Optional<HospitalOrder> findById(Long orderId);
}
