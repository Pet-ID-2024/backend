package com.petid.domain.hospital.model;

public record HospitalLocation(
        Long id,
        Double lat,
        Double lon
) {
    public static HospitalLocation from(Double lat, Double lon) {
        return new HospitalLocation(
                null,
                lat,
                lon
        );
    }
}
