package com.petid.domain.content.repository;

import java.util.Optional;
import com.petid.domain.content.model.ContentLiked;

public interface ContentLikedRepository {
	 // Find like by member and content
    void findByMemberIdAndContentId(Long memberId, Long contentId);

    // Count total likes for a specific content
    long countByContentId(Long contentId);

    // Optionally, delete the like
    void deleteByMemberIdAndContentId(Long memberId, Long contentId);
}
