package com.petid.domain.banner.repository;

import com.petid.domain.banner.model.Banner;
import com.petid.domain.member.model.Member;

import java.util.List;
import java.util.Optional;

public interface BannerRepository {
	List<Banner> findByType(String type);
	
	Banner save(Banner banner);

    Optional<Banner> findById(long memberId);

	Banner updateBanner(Long id, Banner updatedBanner);

	void deleteById(Long id);
	
}
