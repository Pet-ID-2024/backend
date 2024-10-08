package com.petid.infra.content.repository;

import com.petid.domain.content.model.PostLikeInfo;
import com.petid.infra.content.entity.ContentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentJpaRepository extends JpaRepository<ContentEntity, Long> {
	
		@Query("SELECT new com.example.content.model.dto.PostLikeInfo(COUNT(cl), " +
	           "CASE WHEN COUNT(cl2) > 0 THEN true ELSE false END) " +
	           "FROM ContentLike cl " +
	           "LEFT JOIN ContentLike cl2 ON cl2.contentId = :contentId AND cl2.memberId = :memberId " +
	           "WHERE cl.contentId = :contentId")
	    PostLikeInfo getPostLikeInfo(@Param("contentId") Long contentId, @Param("memberId") Long memberId);
}
