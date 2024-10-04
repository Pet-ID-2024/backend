package com.petid.infra.content.repository;

import com.petid.infra.banner.entity.BannerEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJpaRepository extends JpaRepository<BannerEntity, Long> {
	List<BannerEntity> findAllByType(String type);
}
