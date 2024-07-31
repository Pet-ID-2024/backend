package com.petid.infra.banner.entity;

import com.petid.domain.banner.model.Banner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Getter
@Table(name = "banner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String text;
    private String type; 

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public static BannerEntity from(Banner banner) {
        return new BannerEntity(
        		banner.id(),
        		banner.imageUrl(),
        		banner.text(),
        		banner.type()
        		);
    }
    
    public Banner toDomain() {
        return new Banner(
            this.getId(),
            this.getImageUrl(),
            this.getText(),
            this.getType()
        );
    }

	public void setType(String type) {
		this.type = type;
	}
}
