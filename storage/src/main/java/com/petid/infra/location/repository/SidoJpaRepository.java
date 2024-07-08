package com.petid.infra.location.repository;

import com.petid.infra.location.entity.SidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SidoJpaRepository extends JpaRepository<SidoEntity, Long> {
}
