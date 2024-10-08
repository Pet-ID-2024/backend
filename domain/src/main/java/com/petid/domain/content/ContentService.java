package com.petid.domain.content;

import com.petid.domain.content.model.Content;
import com.petid.domain.content.repository.ContentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    // Create new content
    public Content createContent(Content content, long authorId ) {
        return contentRepository.save(content, authorId );
    }

    // Get all contents
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    // Get content by ID
    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    // Update content
    public Optional<Content> updateContent(Long id, Content content) {
        return contentRepository.updateContent(id, content);
    }

    // Delete content
    public boolean deleteContent(Long id) {
        return contentRepository.deleteById(id);
    }
}
