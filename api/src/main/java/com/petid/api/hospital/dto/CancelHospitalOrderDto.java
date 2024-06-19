package com.petid.api.hospital.dto;

public record CancelHospitalOrderDto() {

    public record Request(
            long orderId
    ) {
    }
}
