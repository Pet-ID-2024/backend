package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.HospitalOrderSummaryDTO;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.infra.hospital.entity.HospitalOrderEntity;
import com.petid.infra.hospital.entity.QHospitalEntity;
import com.petid.infra.member.entity.QMemberAuthEntity;
import com.petid.infra.member.entity.QMemberEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
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

    public List<HospitalOrderSummaryDTO> findAllByStatus(
            Long memberId,
            OrderStatus status
    ) {
        QHospitalEntity hospitalEntity = QHospitalEntity.hospitalEntity;
        QMemberEntity memberEntity = QMemberEntity.memberEntity;
        QMemberAuthEntity memberAuthEntity = QMemberAuthEntity.memberAuthEntity;
        
        JPQLQuery<String> itemCountSubquery = JPAExpressions.select(memberAuthEntity.name)
        		.from(memberAuthEntity)
        	    .where(memberAuthEntity.memberId.eq(memberEntity.id));
        
        return queryFactory
                .select(
                        Projections.constructor(HospitalOrderSummaryDTO.class
                                , hospitalOrderEntity.id,
                                memberEntity.id,
                                itemCountSubquery,
                                hospitalEntity.name.as("hospitalName"),
                                hospitalOrderEntity.status,
                                hospitalOrderEntity.createdAt.as("date"))
                )
                .from(hospitalOrderEntity)
                .leftJoin(hospitalEntity).on(hospitalEntity.id.eq(hospitalOrderEntity.hospitalId)) // Left join with hospitalEntity
                .leftJoin(memberEntity).on(memberEntity.id.eq(hospitalOrderEntity.member.id))
                .where(
                        hospitalOrderEntity.member.id.eq(memberId),
                		status.equals(OrderStatus.ALL)
                		? Expressions.TRUE 
                		: hospitalOrderEntity.status.eq(status)
                )
                .fetch();

    }

    public List<HospitalOrderEntity> findAllByHospitalIdAndDateAndStatus(
            Long hospitalId,
            Instant startOfDay,
            Instant endOfDay,
            List<OrderStatus> statuses
    ) {
        return queryFactory
                .selectFrom(hospitalOrderEntity)
                .where(
                        hospitalOrderEntity.hospitalId.eq(hospitalId)
                                .and(hospitalOrderEntity.date.between(startOfDay, endOfDay))
                                .and(hospitalOrderEntity.status.in(statuses))
                )
                .fetch();
    }

}
