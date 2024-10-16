package com.petid.api.hospital.dto;

import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.model.HospitalOrderSummaryDTO;
import com.petid.domain.hospital.type.OrderStatus;

import java.time.Instant;

public record HospitalOrderDto() {

    public record Request(
            long hospitalId,
            Instant date
    ) {
        public HospitalOrder toDomain(
                long memberId
        ) {
            return new HospitalOrder(
                    null,
                    date,
                    hospitalId,
                    OrderStatus.PENDING,
                    memberId
            );
        }
    }

    public record Response(
            Long id,
            long hospitalId,
            Instant date,
            OrderStatus status
    ) {
        public static Response from(
          HospitalOrder hospitalOrder
        ) {
            return new Response(
                    hospitalOrder.id(),
                    hospitalOrder.hospitalId(),
                    hospitalOrder.date(),
                    hospitalOrder.status()
            );
        }
    }

    public record NameResponse(
            Long id,
            String hospitalName,
            Instant date,
            OrderStatus status
    ) {
        public static NameResponse from(
                HospitalOrderSummaryDTO hospitalOrder
        ) {
            return new NameResponse(
                    hospitalOrder.id(),
                    hospitalOrder.hospitalName(),
                    hospitalOrder.date(),
                    hospitalOrder.status()
            );
        }
    }
}
