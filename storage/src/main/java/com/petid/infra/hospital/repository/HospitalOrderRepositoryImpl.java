package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.infra.hospital.entity.HospitalOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HospitalOrderRepositoryImpl implements HospitalOrderRepository {

    private final MemberManager memberManager;
    private final HospitalOrderJpaRepository jpaRepository;

    @Override
    public HospitalOrder save(
            HospitalOrder hospitalOrder
    ) {
        Member member = memberManager.get(hospitalOrder.memberId());

        HospitalOrderEntity hospitalOrderEntity = HospitalOrderEntity.from(
                hospitalOrder,
                member
        );

        return jpaRepository.save(hospitalOrderEntity).toDomain();
    }

    @Override
    public Optional<HospitalOrder> findById(
            Long orderId
    ) {
        return jpaRepository.findById(orderId)
                .map(HospitalOrderEntity::toDomain);
    }

    @Override
	public List<HospitalOrder> findAllByStatus(OrderStatus status) {
		if (status.name().equals("ALL")) {
            return jpaRepository.findAll().stream().map(HospitalOrderEntity::toDomain).toList();
        }
        return jpaRepository.findAllByStatus(status).stream().map(HospitalOrderEntity::toDomain).toList();
	}

	@Override
	public int updateOrderStatus(long orderId, OrderStatus status) {
		return jpaRepository.updateStatusByOrderId(orderId, status);		
	}

}
