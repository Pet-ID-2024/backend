package com.petid.domain.hospital.model;

public record Hospital(
        Long id,
        int sidoId,
        int sigunguId,
        int eupmundongId,
        String imageUrl,
        String address,
        String name,
        String hours,
        String tel,
        String vet
) {
}
