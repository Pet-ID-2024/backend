package com.petid.infra.content.repository;

import com.petid.infra.content.entity.ContentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJpaRepository extends JpaRepository<ContentEntity, Long> {	
}
