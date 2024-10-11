package com.petid.infra.content.repository;

import com.petid.infra.content.entity.ContentLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentLikedJpaRepository extends JpaRepository<ContentLikedEntity, Long> {
	
}
