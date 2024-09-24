package com.petid.domain.hospital.repository;

import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public interface HospitalOrderRepository {
    HospitalOrder save(HospitalOrder hospitalOrder);

    Optional<HospitalOrder> findById(Long orderId);
    
    List<HospitalOrder> findAllByStatus(OrderStatus status);
    
    int updateOrderStatus(long orderId, OrderStatus status);

    List<HospitalOrder> findAllByHospitalIdAndDateAndStatusValid(Long hospitalId, LocalDate date, ZoneId zoneId);
}
