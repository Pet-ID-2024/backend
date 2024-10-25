package com.petid.domain.content;

import com.petid.domain.content.model.Content;
import com.petid.domain.content.repository.ContentLikedRepository;
import com.petid.domain.content.repository.ContentRepository;
import com.petid.domain.type.Category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentLikedRepository contentLikedRepository;

    // Create new content
    public Content createContent(Content content, long authorId ) {
        return contentRepository.save(content, authorId );
    }

    // Get all contents
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }
    
    public List<Content> getContentsByCategory(Category category, long memberId) {
        return contentRepository.findByCategory(category, memberId);
    }

    // Get content by ID
    public Content getContentById(long id, long memberId) {
        return contentRepository.findById(id, memberId);
    }

    // Update content
    public Content updateContent(long id, Content content) {
        return contentRepository.updateContent(id, content);
    }

    // Delete content
    public void deleteContent(long id) {
        contentRepository.deleteById(id);
    }
    
    @Transactional
    public void likeContent(Long memberId, Long contentId) {
        // Check if the member already liked the content
        contentLikedRepository.findByMemberIdAndContentId(memberId, contentId);        
          
    }

    @Transactional
    public void unlikeContent(Long memberId, Long contentId) {
        // Remove the like if it exists
        contentLikedRepository.deleteByMemberIdAndContentId(memberId, contentId);
    }
    

    public long getCount(long contentId) {
    	return contentLikedRepository.countByContentId(contentId);
    }
}
