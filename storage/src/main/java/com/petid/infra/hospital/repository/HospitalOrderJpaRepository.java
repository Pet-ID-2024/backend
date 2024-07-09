package com.petid.infra.hospital.repository;

import com.petid.infra.hospital.entity.HospitalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalOrderJpaRepository extends JpaRepository<HospitalOrderEntity, Long> {
}
