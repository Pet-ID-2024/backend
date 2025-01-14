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
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "banner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String text;
    private String type; 
    private String status;
    private Long contentId;
    private String createdAt; 
    private String updatedAt; 
   
    
    public static BannerEntity from(Banner banner) {
        return new BannerEntity(
        		banner.id(),
        		banner.imageUrl(),
        		banner.text(),
        		banner.type(),
                banner.status(),
                banner.contentId(),
                null,
                null
        		);
    }
    
    public Banner toDomain() {
        return new Banner(
            this.getId(),
            this.getImageUrl(),
            this.getText(),
            this.getType(),
            this.getStatus(),
            this.getContentId()
        );
    }

	public void setType(String type) {
		this.type = type;
	}
}
