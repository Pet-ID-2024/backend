package com.petid.infra.location.repository;

import com.petid.infra.location.entity.SigunguEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SigunguJpaRepository extends JpaRepository<SigunguEntity, Long> {
    List<SigunguEntity> findAllBySidoId(long sidoId);
}
