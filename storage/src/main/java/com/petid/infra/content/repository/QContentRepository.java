package com.petid.infra.content.repository;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.petid.infra.content.entity.QContentEntity;
import com.petid.infra.content.entity.QContentLikedEntity;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QContentRepository {

    private final JPAQueryFactory queryFactory;

    public List<Content> findAllByCategory(Category category, long memberId, Boolean isFullBody) {
        QContentEntity content = QContentEntity.contentEntity;
        QContentLikedEntity contentLiked =  QContentLikedEntity.contentLikedEntity;
        
        BooleanExpression isLiked = JPAExpressions.selectOne()
        	    .from(contentLiked)
        	    .where(contentLiked.contentId.eq(content.contentId)
        	           .and(contentLiked.memberId.eq(memberId)))
        	    .exists();
        
        return queryFactory.select(Projections.constructor(
                Content.class, // Project fields into a DTO
                content.contentId,
                content.title,
                (isFullBody ? content.body : content.body.substring(0, 50)),
                content.category,
                content.imageUrl,
                content.createdAt,
                content.updatedAt,
                contentLiked.likeId.count().as("likesCount"),
                content.authorId,
                isLiked // Check if content is liked by the member
            ))
            .from(content)
            .leftJoin(contentLiked)
               .on(content.contentId.eq(contentLiked.contentId))
            .where(category.equals(Category.ALL) ? null : content.category.eq(category))
            .groupBy(content.contentId)
            .orderBy(content.createdAt.desc())
            .fetch();
    }
    
    public Content findById(long contentId, long memberId) {
        QContentEntity content = QContentEntity.contentEntity;
        QContentLikedEntity contentLiked =  QContentLikedEntity.contentLikedEntity;
        
        BooleanExpression isLiked = JPAExpressions.selectOne()
        	    .from(contentLiked)
        	    .where(contentLiked.contentId.eq(content.contentId)
        	           .and(contentLiked.memberId.eq(memberId)))
        	    .exists();
        
        return queryFactory.select(Projections.constructor(
                Content.class, // Project fields into a DTO
                content.contentId,
                content.title,
                content.body,
                content.category,
                content.imageUrl,
                content.createdAt,
                content.updatedAt,
                contentLiked.likeId.count().as("likesCount"),
                content.authorId,
                isLiked// Check if content is liked by the member
            ))
            .from(content)
            .leftJoin(contentLiked)
               .on(content.contentId.eq(contentLiked.contentId))
            .where(content.contentId.eq(contentId))
            .groupBy(content.contentId)
            .fetchFirst();
    }

}
