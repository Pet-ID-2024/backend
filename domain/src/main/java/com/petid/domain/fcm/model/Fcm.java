package com.petid.domain.fcm.model;

public record Fcm(
	String title,
    String body,
    String image,
    String target,
    String targetType
){
	public Fcm updateFcmToken(
            String fcmToken
    ) {
        return new Fcm(
        		title,
        		body,
        		image,
        		fcmToken,
        		targetType                
        );
    }
}



