package com.petid.infra.hospital.repository;

import com.petid.infra.hospital.entity.HospitalEntity;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.petid.infra.hospital.entity.QHospitalEntity.hospitalEntity;


@Repository
@RequiredArgsConstructor
public class QHospitalRepository {

    private final JPAQueryFactory queryFactory;

    public List<HospitalEntity> findAllByLocation(
            long sidoId,
            long sigunguId,
            List<Long> eupmundongIds
    ) {
        return queryFactory
                .selectFrom(hospitalEntity)
                .where(
                        hospitalEntity.sidoId.eq(sidoId),
                        hospitalEntity.sigunguId.eq(sigunguId),
                        hospitalEntity.eupmundongId.in(eupmundongIds)
                )
                .fetch();
    }

    public List<HospitalEntity> findAllByLocationIdsOrderByLocation(
            long sidoId,
            long sigunguId,
            List<Long> eupmundongIds,
            double lat,
            double lon
    ) {
        String userLocation = "POINT(" + lon + " " + lat + ")";

        return queryFactory.selectFrom(hospitalEntity)
                .where(
                        hospitalEntity.sidoId.eq(sidoId),
                        hospitalEntity.sigunguId.eq(sigunguId),
                        hospitalEntity.eupmundongId.in(eupmundongIds)
                )
                .orderBy(
                        Expressions.numberTemplate(Double.class,
                                "ST_Distance_Sphere({0}, {1})", hospitalEntity.location, userLocation).asc()
                )
                .fetch();
    }
}
