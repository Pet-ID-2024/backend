package com.petid.domain.hospital.model;

public record Hospital(
        long id,
        String name,
        String hours,
        String tel,
        String vet
) {
}
