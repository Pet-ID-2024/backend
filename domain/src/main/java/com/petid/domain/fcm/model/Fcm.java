package com.petid.domain.fcm.model;

import java.util.Map;

public record Fcm(
	String title,
    Map<String, Object> body,    
    String target,
    String targetType
){
	public Fcm updateFcmToken(
            String fcmToken
    ) {
        return new Fcm(
        		title,
        		body,        		
        		fcmToken,
        		targetType                
        );
    }
}



