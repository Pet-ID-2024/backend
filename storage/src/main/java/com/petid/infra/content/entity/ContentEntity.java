package com.petid.infra.content.entity;

import java.time.Instant;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "likes_count", columnDefinition = "INT(11) DEFAULT 0")
    private long likesCount;

    private long authorId;
   
    
    public Content toDomain() {
    	return new Content(
		        contentId,
		        title,
		        body,
		        category,
		        imageUrl,
		        createdAt,
		        updatedAt,
		        likesCount,
		        authorId
        );
    }

    // Convert domain model to entity
    public static ContentEntity from(Content domain, long authorId) {
    	ContentEntity entity = new ContentEntity();
        entity.setContentId(domain.contentId());
        entity.setTitle(domain.title());
        entity.setBody(domain.body());
        entity.setCategory(domain.category());
        entity.setImageUrl(domain.imageUrl());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        entity.setLikesCount(domain.likesCount());
        entity.setAuthorId(authorId);  // Set the Member entity here
        return entity;
    }
}
