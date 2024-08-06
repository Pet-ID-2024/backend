package com.petid.infra.hospital.repository;

import com.petid.infra.hospital.entity.HospitalEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//import static com.petid.infra.hospital.entity.QHospitalEntity.hospitalEntity;

@Repository
@RequiredArgsConstructor
public class QHospitalRepository {

    private final JPAQueryFactory queryFactory;

    public List<HospitalEntity> findAllByLocation(
            long sidoId,
            long sigunguId,
            List<Long> eupmundongIds
    ) {
        return null; /*queryFactory
                .selectFrom(hospitalEntity)
                .where(
                        hospitalEntity.sidoId.eq(sidoId),
                        hospitalEntity.sigunguId.eq(sigunguId),
                        hospitalEntity.eupmundongId.in(eupmundongIds)
                )
                .fetch();*/ 
    }
}
