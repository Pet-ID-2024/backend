package com.petid.infra.content.repository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petid.domain.content.model.Content;
import com.petid.domain.content.repository.ContentRepository;
import com.petid.domain.member.model.Member;
import com.petid.infra.banner.entity.BannerEntity;
import com.petid.infra.content.entity.ContentEntity;

@Repository
@RequiredArgsConstructor
public class ConetneRepositoryImpl implements ContentRepository {

	private final ContentJpaRepository ContentJpaRepository;

	@Override
	public Content save(Content content, long authorId ) {
		ContentEntity bannerEntity = ContentEntity.from(content, authorId);
		ContentEntity savedBannerEntity =  ContentJpaRepository.save(bannerEntity);
		return savedBannerEntity.toDomain();
		
	}


	@Override
	public List<Content> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Content> findById(long contentId) {
		ContentJpaRepository.getPostLikeInfo(contentId, null);
		
		return Optional.empty();
	}

	@Override
	public Optional<Content> updateContent(Long id, Content updatedContent) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
