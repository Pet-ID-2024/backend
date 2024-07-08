package com.petid.domain.hospital.model;

public record Hospital(
        Long id,
        long sidoId,
        long sigunguId,
        long eupmundongId,
        String imageUrl,
        String address,
        String name,
        String hours,
        String tel,
        String vet
) {
}
