package com.petid.domain.content.model;

import java.time.LocalDateTime;

import com.petid.domain.type.Category;

public record Content(
    Long contentId,
    String title,
    String body,
    Category category,
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Integer likesCount,
    Long authorId
) {
   
}