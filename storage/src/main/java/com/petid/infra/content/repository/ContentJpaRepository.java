package com.petid.infra.content.repository;

import com.petid.domain.content.model.PostLikeInfo;
import com.petid.infra.content.entity.ContentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentJpaRepository extends JpaRepository<ContentEntity, Long> {
	
}
