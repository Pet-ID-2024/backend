package com.petid.domain.hospital.model;

import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrder(
        Long id,
        String uid,
        long hospitalId,
        Instant date,
        OrderStatus status
) {
    public HospitalOrder update(
            Instant date
    ) {
        return new HospitalOrder(
                id,
                uid,
                hospitalId,
                date,
                status
        );
    }

    public HospitalOrder cancel() {
        return new HospitalOrder(
                id,
                uid,
                hospitalId,
                date,
                OrderStatus.CANCELLED
        );
    }
}
