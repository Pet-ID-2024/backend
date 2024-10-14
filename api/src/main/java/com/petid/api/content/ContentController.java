package com.petid.api.content;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petid.api.common.RequestUtil;
import com.petid.domain.content.ContentService;
import com.petid.domain.content.model.Content;
import com.petid.domain.type.Category;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/content")
@RequiredArgsConstructor
public class ContentController {

	private final ContentService contentService;

    // Create a new content
    @PostMapping
    public ResponseEntity<Content> createContent(HttpServletRequest request, @RequestBody Content content) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
        Content createdContent = contentService.createContent(content, memberId);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }

    // Get all contents
    @GetMapping
    public ResponseEntity<List<Content>> getContentsByCategory(HttpServletRequest request, @RequestParam Category category) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
        List<Content> contents = contentService.getContentsByCategory(category, memberId);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    // Get content by ID
    @GetMapping("/{contentId}")
    public ResponseEntity<Content> getContentById(HttpServletRequest request, @PathVariable long contentId) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
        Content content = contentService.getContentById(contentId, memberId);
        return new ResponseEntity<>(content, HttpStatus.OK);                      
    }

    // Update content by ID
    @PutMapping("/{contentId}")
    public ResponseEntity<Content> updateContent(@PathVariable long contentId, @RequestBody Content content) {
        Optional<Content> updatedContent = contentService.updateContent(contentId, content);
        return updatedContent.map(ResponseEntity::ok)
                             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete content by ID
    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable long contentId) {
        boolean isDeleted = contentService.deleteContent(contentId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/{contentId}/like")
    public ResponseEntity<Void> likeContent(HttpServletRequest request, @PathVariable Long contentId) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
    	contentService.likeContent(memberId, contentId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{contentId}/like")
    public ResponseEntity<Void> unlikeContent(HttpServletRequest request, @PathVariable Long contentId) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
    	contentService.unlikeContent(memberId, contentId);
        return ResponseEntity.ok().build();
    }

}