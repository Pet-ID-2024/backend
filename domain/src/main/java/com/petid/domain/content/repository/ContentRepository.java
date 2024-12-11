package com.petid.domain.content.repository;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;

import java.util.List;

public interface ContentRepository {
	Content save(Content content, long authorId);
	
	List<Content> findAll();
	
	List<Content> findByCategory(Category category, long memberId, boolean isFullBody);
	
	Content findById(long contentId, long memberId);

	Content updateContent(Long id, Content updatedContent);

	void deleteById(Long id);
}
