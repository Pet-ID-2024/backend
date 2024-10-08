package com.petid.domain.content.repository;

import java.util.List;
import java.util.Optional;

import com.petid.domain.content.model.Content;

public interface ContentRepository {
	Content save(Content content, long authorId);
	
	List<Content> findAll();
	
	Optional<Content> findById(long contentId);

	Optional<Content> updateContent(Long id, Content updatedContent);

	boolean deleteById(Long id);
}
