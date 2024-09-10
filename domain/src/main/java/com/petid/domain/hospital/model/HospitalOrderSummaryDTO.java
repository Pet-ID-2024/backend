package com.petid.domain.hospital.model;

import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrderSummaryDTO(Long id, Long memberId, String email, String hospitalName, OrderStatus status, Instant date ) {
}
