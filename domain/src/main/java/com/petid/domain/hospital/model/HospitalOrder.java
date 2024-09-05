package com.petid.domain.hospital.model;

import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrder(
        Long id,
        Instant date,
        long hospitalId,
        OrderStatus status,
        long memberId
) {
    public HospitalOrder update(
            Instant date
    ) {
        return new HospitalOrder(
                id,
                date,
                hospitalId,
                status,
                memberId
        );
    }

    public HospitalOrder cancel() {
        return new HospitalOrder(
                id,
                date,
                hospitalId,
                OrderStatus.CANCELLED,
                memberId
        );
    }
}
