package com.petid.domain.hospital.repository;

import java.util.List;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;

import java.util.Optional;

public interface HospitalOrderRepository {
    HospitalOrder save(HospitalOrder hospitalOrder);

    Optional<HospitalOrder> findById(Long orderId);
    
    List<HospitalOrder> findAllByStatus(OrderStatus status);
}
