package com.petid.batch.model;

import com.petid.domain.hospital.model.Hospital;

public record HospitalItemModel(
        String orgAddr,
        String orgAddrDtl,
        String memberNm,
        String orgNm,
        String tel
) {
    public Hospital toDomain(
            long sidoId,
            long sigunguId,
            long eupmundongId
    ) {
        return new Hospital(
                null,
                sidoId,
                sigunguId,
                eupmundongId,
                null,
                orgAddr + " " + orgAddrDtl,
                orgNm,
                null,
                tel,
                memberNm
        );
    }
}
