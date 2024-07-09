package com.petid.api.hospital.dto;

import java.time.Instant;

public record UpdateHospitalOrderDto() {

    public record Request(
            long orderId,
            Instant date
    ) {
    }
}
