package com.petid.infra.banner.repository;

import com.petid.domain.banner.model.Banner;
import com.petid.domain.banner.repository.BannerRepository;

import com.petid.infra.banner.entity.BannerEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BannerRepositoryImpl implements BannerRepository {

    private final BannerJpaRepository bannerJpaRepository;


	@Override
	public List<Banner> findByType(String type) {
		if (type.equals("ALL")){
			return bannerJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"))
					.stream().map(BannerEntity::toDomain).collect(Collectors.toList());
		}
		return bannerJpaRepository.findAllByType(type).stream().map(BannerEntity::toDomain).collect(Collectors.toList());
	}


	@Override
	public Banner save(Banner banner) {
		BannerEntity bannerEntity = BannerEntity.from(banner);
		BannerEntity savedBannerEntity =  bannerJpaRepository.save(bannerEntity);
		return savedBannerEntity.toDomain();
	}


	@Override
	public Optional<Banner> findById(long bannerId) {
		// TODO Auto-generated method stub
		return bannerJpaRepository.findById(bannerId).map(BannerEntity::toDomain);
	}


	@Override
	public Banner updateBanner(Long id, Banner updatedBanner) {
		Optional<BannerEntity> optionalBannerEntity = bannerJpaRepository.findById(id);
		  if (optionalBannerEntity.isPresent()) {
			BannerEntity bannerEntity = optionalBannerEntity.get();
			BannerEntity updatedBannerEntity = BannerEntity.from(updatedBanner);
			updatedBannerEntity.setId(bannerEntity.getId());
			updatedBannerEntity.setImageUrl(updatedBanner.imageUrl());
			updatedBannerEntity.setText(updatedBanner.text());
			updatedBannerEntity.setType(updatedBanner.type());
            bannerJpaRepository.save(updatedBannerEntity).toDomain();
            return  updatedBannerEntity.toDomain();
		 }
        throw new RuntimeException("Banner not found");
	}


	@Override
	public void deleteById(Long id) {
		bannerJpaRepository.deleteById(id);
	}
}
