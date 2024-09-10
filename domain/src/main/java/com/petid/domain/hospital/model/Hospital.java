package com.petid.domain.hospital.model;

import java.util.List;

public record Hospital(
        Long id,
        long sidoId,
        long sigunguId,
        long eupmundongId,
        List<String> imageUrl,
        String address,
        String name,
        String hours,
        String tel,
        String vet,
        HospitalLocation location
) {
}
