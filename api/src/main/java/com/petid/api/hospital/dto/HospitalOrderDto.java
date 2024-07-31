package com.petid.api.hospital.dto;

import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrderDto() {

    public record Request(
            long hospitalId,
            Instant date
    ) {
        public HospitalOrder toDomain(
                String uid
        ) {
            return new HospitalOrder(
                    null,
                    uid,
                    hospitalId,
                    date,
                    OrderStatus.PENDING
            );
        }
    }

    public record Response(
            Long id,
            long hospitalId,
            Instant date
    ) {
        public static Response from(
          HospitalOrder hospitalOrder
        ) {
            return new Response(
                    hospitalOrder.id(),
                    hospitalOrder.hospitalId(),
                    hospitalOrder.date()
            );
        }
    }
}
