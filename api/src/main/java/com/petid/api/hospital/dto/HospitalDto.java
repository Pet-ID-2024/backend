package com.petid.api.hospital.dto;

import com.petid.domain.hospital.model.Hospital;

import java.util.List;

public record HospitalDto() {
    public record Response(
            long id,
            List<String> imageUrl,
            String address,
            String name,
            String hours,
            String tel,
            String vet
    ) {
        public static HospitalDto.Response from(Hospital hospital) {
            return new HospitalDto.Response(
                    hospital.id(),
                    hospital.imageUrl(),
                    hospital.address(),
                    hospital.name(),
                    hospital.hours(),
                    hospital.tel(),
                    hospital.vet()
            );
        }
    }
}
