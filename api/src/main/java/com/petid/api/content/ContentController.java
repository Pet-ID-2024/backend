package com.petid.api.content;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petid.api.common.RequestUtil;
import com.petid.domain.content.ContentService;
import com.petid.domain.content.model.Content;
import com.petid.domain.pet.service.S3Service;
import com.petid.domain.type.Category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<List<Content>> getContentsByCategory(HttpServletRequest request, @RequestParam Category category,  @RequestParam(defaultValue = "false") boolean isFullBody) {
    	long memberId = RequestUtil.getMemberIdFromRequest(request);
        List<Content> contents = contentService.getContentsByCategory(category, memberId,isFullBody);
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
        Content updatedContent = contentService.updateContent(contentId, content);
        return new ResponseEntity<>(updatedContent, HttpStatus.OK);      
    }

    // Delete content by ID
    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable long contentId) {
        contentService.deleteContent(contentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
    
    @DeleteMapping("/images/{contentId}")
    public ResponseEntity<String> deleteImageInS3(@RequestBody String filePath) {
      String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
  	  String url = S3service.deleteImage(decodedFilePath);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
    

}