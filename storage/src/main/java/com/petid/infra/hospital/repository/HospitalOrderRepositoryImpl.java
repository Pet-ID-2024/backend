package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.manager.HospitalManager;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.infra.hospital.entity.HospitalOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HospitalOrderRepositoryImpl implements HospitalOrderRepository {

    private final MemberManager memberManager;
    private final HospitalManager hospitalManager;
    private final HospitalOrderJpaRepository jpaRepository;

    @Override
    public HospitalOrder save(
            HospitalOrder hospitalOrder
    ) {
        Member member = memberManager.getByUid(hospitalOrder.uid());
        Hospital hospital = hospitalManager.get(hospitalOrder.hospitalId());

        HospitalOrderEntity hospitalOrderEntity = HospitalOrderEntity.from(
                hospitalOrder,
                member,
                hospital
        );

        return jpaRepository.save(hospitalOrderEntity).toDomain(member.uid());
    }

    @Override
    public Optional<HospitalOrder> findById(
            Long orderId
    ) {
        return jpaRepository.findById(orderId)
                .map(orderEntity -> {
                    Member member = memberManager.get(orderEntity.getMemberId());
                    return orderEntity.toDomain(member.uid());
                });
    }
}
