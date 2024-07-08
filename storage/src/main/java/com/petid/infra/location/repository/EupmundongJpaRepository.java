package com.petid.infra.location.repository;

import com.petid.infra.location.entity.EupmundongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EupmundongJpaRepository extends JpaRepository<EupmundongEntity, Long> {
    List<EupmundongEntity> findAllBySigunguId(long sigunguId);
}
