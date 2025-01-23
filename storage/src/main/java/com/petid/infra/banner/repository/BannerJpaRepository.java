package com.petid.infra.banner.repository;

import com.petid.infra.banner.entity.BannerEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerJpaRepository extends JpaRepository<BannerEntity, Long> {
	List<BannerEntity> findAllByType(String type);
	List<BannerEntity> findAllByTypeOrderByUpdatedAtDesc(String type);
}
