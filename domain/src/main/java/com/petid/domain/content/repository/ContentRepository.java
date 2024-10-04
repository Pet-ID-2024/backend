package com.petid.domain.content.repository;

import com.petid.domain.banner.model.Banner;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
	List<Banner> findByType(String type);
	
	Banner save(Banner banner);

    Optional<Banner> findById(long memberId);

	Banner updateBanner(Long id, Banner updatedBanner);

	void deleteById(Long id);
	
}
