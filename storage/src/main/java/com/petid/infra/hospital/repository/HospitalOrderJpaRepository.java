package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.type.OrderStatus;
import com.petid.infra.hospital.entity.HospitalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HospitalOrderJpaRepository extends JpaRepository<HospitalOrderEntity, Long> {
    List<HospitalOrderEntity> findAllByStatus(OrderStatus status);
}
