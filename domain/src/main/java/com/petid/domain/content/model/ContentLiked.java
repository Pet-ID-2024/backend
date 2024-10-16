package com.petid.domain.content.model;

import java.time.Instant;
import java.time.LocalDateTime;

import com.petid.domain.type.Category;

public record ContentLiked(
    long likeId,
    long memberId,
    long contentId,
    Instant createdAt
) {
  
}