package com.petid.domain.content.repository;

import java.util.List;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;

public interface ContentRepository {
	Content save(Content content, long authorId);
	
	List<Content> findAll();
	
	List<Content> findByCategory(Category category, long memberId);
	
	Content findById(long contentId, long memberId);

	Content updateContent(Long id, Content updatedContent);

	void deleteById(Long id);
}
