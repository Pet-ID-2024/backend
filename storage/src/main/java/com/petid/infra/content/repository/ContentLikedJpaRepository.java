package com.petid.infra.content.repository;


import com.petid.infra.content.entity.ContentLikedEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentLikedJpaRepository extends JpaRepository<ContentLikedEntity, Long> {
	 // Find like by member and content
    Optional<ContentLikedEntity> findByMemberIdAndContentId(Long memberId, Long contentId);

    // Count total likes for a specific content
    Long countByContentId(Long contentId);

    // Optionally, delete the like
    void deleteByMemberIdAndContentId(Long memberId, Long contentId);
}
