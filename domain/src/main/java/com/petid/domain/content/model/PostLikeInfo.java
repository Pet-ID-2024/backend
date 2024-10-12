package com.petid.domain.content.model;


public class PostLikeInfo {
    private Long likeCount;
    private boolean likedByCurrentUser;

    // Constructor
    public PostLikeInfo(Long likeCount, boolean likedByCurrentUser) {
        this.likeCount = likeCount;
        this.likedByCurrentUser = likedByCurrentUser;
    }

    // Getters
    public Long getLikeCount() {
        return likeCount;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }
}

