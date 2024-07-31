package com.petid.api.banner;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petid.domain.banner.BannerService;
import com.petid.domain.banner.model.Banner;
import com.petid.domain.pet.service.S3Service;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;
    private final S3Service S3service;  


    @GetMapping("/type")
    public List<Banner> getBannersByType(@RequestParam String type) {
        return bannerService.getBannersByType(type);
    }

    @PostMapping
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerService.createBanner(banner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable Long id, @RequestBody Banner updatedBanner) {
        try {
            return ResponseEntity.ok(bannerService.updateBanner(id, updatedBanner));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/presigned-get-url")
    public ResponseEntity<String> getPetImageBucketUrl(@RequestParam String filePath) {
  	  String url = S3service.createPresignedGetUrl(filePath);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }

    @PostMapping("/presigned-put-url")
    public ResponseEntity<String> putPetImageBucketUrl(@RequestBody String filePath) {
  	  String url = S3service.createPresignedPutUrl(filePath);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
    

}