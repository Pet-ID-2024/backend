package com.petid.api.content;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petid.api.common.RequestUtil;
import com.petid.domain.content.ContentService;
import com.petid.domain.content.model.Content;
import com.petid.domain.pet.service.S3Service;
import com.petid.domain.type.Category;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/content")
@RequiredArgsConstructor
public class ContentController {

	private final ContentService contentService;
	private final S3Service S3service;  

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
    public ResponseEntity<Map<String, Object>> likeContent(HttpServletRequest request, @PathVariable Long contentId) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
    	contentService.likeContent(memberId, contentId);
    	Map<String, Object> response = new HashMap<>();
    	response.put("likeCount", contentService.getCount(contentId));
    	response.put("contentId", contentId);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{contentId}/like")
    public ResponseEntity<Map<String, Object>> unlikeContent(HttpServletRequest request, @PathVariable Long contentId) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
    	contentService.unlikeContent(memberId, contentId);
    	Map<String, Object> response = new HashMap<>();
    	response.put("likeCount", contentService.getCount(contentId));
    	response.put("contentId", contentId);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/presigned-get-url")
    public ResponseEntity<String> getContentImageBucketUrl(@RequestParam String filePath) {
      String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
  	  String url = S3service.createPresignedGetUrl(decodedFilePath);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }

    @PostMapping("/presigned-put-url")
    public ResponseEntity<String> putContentImageBucketUrl(@RequestBody String filePath) {
      String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
  	  String url = S3service.createPresignedPutUrl(decodedFilePath);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
    

}