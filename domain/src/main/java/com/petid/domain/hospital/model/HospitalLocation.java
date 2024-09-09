package com.petid.domain.hospital.model;

public record HospitalLocation(
        Long id,
        Double lon,
        Double lat
) {
    public static HospitalLocation from(Double lon, Double lat) {
        return new HospitalLocation(
                null,
                lon,
                lat
        );
    }
}
