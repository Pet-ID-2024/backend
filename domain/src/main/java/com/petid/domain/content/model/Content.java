package com.petid.domain.content.model;

import java.time.Instant;
import java.time.LocalDateTime;

import com.petid.domain.type.Category;

public record Content(
    long contentId,
    String title,
    String body,
    Category category,
    String imageUrl,
    Instant createdAt,
    Instant updatedAt,
    Integer likesCount,
    long authorId,
    Boolean isLiked
) {
  public  Content(
		    long contentId,
		    String title,
		    String body,
		    Category category,
		    String imageUrl,
		    Instant createdAt,
		    Instant updatedAt,
		    Integer likesCount,
		    long authorId) {
	  this(
	            contentId, 
	            title, 
	            body, 
	            category, 
	            imageUrl, 
	            createdAt,
	            updatedAt, 
	            likesCount, 
	            authorId, 
	            null  
	        );
  }
}