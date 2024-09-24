package com.petid.infra.hospital.repository;

import com.petid.infra.hospital.entity.HospitalOrderEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

import static com.petid.infra.hospital.entity.QHospitalOrderEntity.hospitalOrderEntity;

@Repository
@RequiredArgsConstructor
public class QHospitalOrderRepository {

    private final JPAQueryFactory queryFactory;

    public List<HospitalOrderEntity> findAllByHospitalIdAndDate(
            Long hospitalId,
            Instant startOfDay,
            Instant endOfDay
    ) {
        return queryFactory
                .selectFrom(hospitalOrderEntity)
                .where(
                        hospitalOrderEntity.hospitalId.eq(hospitalId)
                                .and(hospitalOrderEntity.date.between(startOfDay, endOfDay))
                )
                .fetch();
    }
}
