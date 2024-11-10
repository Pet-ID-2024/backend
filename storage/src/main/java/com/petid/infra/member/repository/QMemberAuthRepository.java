package com.petid.infra.member.repository;

import com.petid.domain.member.model.MemberAuthInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.petid.infra.member.entity.QMemberAuthEntity.memberAuthEntity;
import static com.petid.infra.pet.entity.QPetEntity.petEntity;


@Repository
@RequiredArgsConstructor
public class QMemberAuthRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<MemberAuthInfo> findMemberInfo(long memberId) {
        return Optional.ofNullable(
                queryFactory
                        .select(
                                Projections.constructor(MemberAuthInfo.class,
                                        memberAuthEntity.id,
                                        memberAuthEntity.memberId,
                                        memberAuthEntity.name,
                                        memberAuthEntity.image,
                                        memberAuthEntity.address,
                                        memberAuthEntity.addressDetails,
                                        memberAuthEntity.phone,
                                        petEntity.id
                                )
                        )
                        .from(memberAuthEntity)
                        .leftJoin(petEntity).on(petEntity.ownerId.eq(memberAuthEntity.memberId))
                        .where(memberAuthEntity.memberId.eq(memberId))
                        .fetchOne());

    }
}
