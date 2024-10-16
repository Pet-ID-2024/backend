package com.petid.domain.content.repository;

import java.util.List;
import java.util.Optional;

import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;

public interface ContentRepository {
	Content save(Content content, long authorId);
	
	List<Content> findAll();
	
	List<Content> findByCategory(Category category, long memberId);
	
	Content findById(long contentId, long memberId);

	Optional<Content> updateContent(Long id, Content updatedContent);

	boolean deleteById(Long id);
}
