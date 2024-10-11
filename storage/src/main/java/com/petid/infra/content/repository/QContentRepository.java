package com.petid.infra.content.repository;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;
import com.querydsl.core.types.Projections;
import com.petid.infra.content.entity.QContentEntity;
import com.petid.infra.content.entity.QContentLikedEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QContentRepository {

    private final JPAQueryFactory queryFactory;

    public List<Content> findAllByCategory(Category category, long memberId) {
        QContentEntity content = QContentEntity.contentEntity;
        QContentLikedEntity contentLiked =  QContentLikedEntity.contentLikedEntity;
        
        
        return queryFactory.select(Projections.constructor(
                Content.class, // Project fields into a DTO
                content.contentId,
                content.title,
                content.body,
                content.category,
                content.imageUrl,
                content.createdAt,
                content.updatedAt,
                content.likesCount,
                content.authorId,
                contentLiked.memberId.eq(memberId).as("isLiked") // Check if content is liked by the member
            ))
            .from(content)
            .leftJoin(contentLiked)
               .on(content.contentId.eq(contentLiked.contentId))
            .where(content.category.eq(category))
            .groupBy(contentLiked.contentId)
            .fetch();
    }

}
