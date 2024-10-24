package com.petid.domain.hospital.model;

import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrderSummaryDTO(Long id, Long memberId, String name, String hospitalName, OrderStatus status, Instant date ) {
}
