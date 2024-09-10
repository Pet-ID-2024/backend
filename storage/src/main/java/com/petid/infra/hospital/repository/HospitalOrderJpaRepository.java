package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.type.OrderStatus;
import com.petid.infra.hospital.entity.HospitalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalOrderJpaRepository extends JpaRepository<HospitalOrderEntity, Long> {
	List<HospitalOrderEntity> findAllByStatus(@Param("status") OrderStatus status);
    
    @Modifying
    @Query("UPDATE hospital_order h SET h.status = :status WHERE h.id = :id")
    int updateStatusByOrderId(@Param("id") Long id, @Param("status") OrderStatus status);
}
