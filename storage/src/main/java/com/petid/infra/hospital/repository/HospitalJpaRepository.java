package com.petid.infra.hospital.repository;

import com.petid.infra.hospital.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalJpaRepository extends JpaRepository<HospitalEntity, Long> {
}
