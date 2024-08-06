package com.petid.domain.banner.model;

public record Banner(
	    Long id,
	    String imageUrl,
	    String text,
	    String type,
		String status
	) {}