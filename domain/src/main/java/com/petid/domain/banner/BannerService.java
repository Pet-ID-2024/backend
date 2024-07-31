package com.petid.domain.banner;

import com.petid.domain.banner.model.Banner;
import com.petid.domain.banner.repository.BannerRepository;
import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.model.Sido;
import com.petid.domain.location.model.Sigungu;
import com.petid.domain.location.repository.EupmundongRepository;
import com.petid.domain.location.repository.SidoRepository;
import com.petid.domain.location.repository.SigunguRepository;
import com.petid.domain.pet.service.S3Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final S3Service s3Service;

    
    
    public Banner createBanner(Banner bannerDto) {        
        return bannerRepository.save(bannerDto);
    }

    public Banner updateBanner(Long id, Banner updatedBanner) {
    	return bannerRepository.updateBanner(id, updatedBanner);
    	
    }

    public void deleteBanner(Long id) {
        bannerRepository.deleteById(id);
    }
    public List<Banner> getBannersByType(String type) {
    	return bannerRepository.findByType(type);
    }

    public String getPresignedGetUrl(String filename) {
        return s3Service.createPresignedGetUrl(filename);
    }

    public String getPresignedPutUrl(String filename) {
        return s3Service.createPresignedPutUrl(filename);
    }
}