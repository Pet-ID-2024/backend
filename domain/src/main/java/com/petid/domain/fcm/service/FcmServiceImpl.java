package com.petid.domain.fcm.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.petid.domain.fcm.model.Fcm;

@Service
public class FcmServiceImpl implements FcmService{

	/**
     * 푸시 메시지 처리를 수행하는 비즈니스 로직
     *
     * @param fcm 모바일에서 전달받은 Object
     * @return 성공(1), 실패(0)
     */
	public void sendNotificationToUser(Fcm fcm) {
		String image = "";
		if (fcm.body().get("image") != null) {
			image = fcm.body().get("image").toString();
		}
		
		Notification notification  = Notification.builder()
				.setTitle(fcm.title())
				.setBody(fcm.body().toString())
				.setImage(image)
				.build();
        Message msg = Message.builder()
        		.setNotification(notification)
                .setToken(fcm.target())
                .build();
        try {
          FirebaseMessaging.getInstance().send(msg);
        } catch (FirebaseMessagingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public void sendNotificationByTopic(Fcm fcm)  {
    	String image = "";
		if (fcm.body().get("image") != null) {
			image = fcm.body().get("image").toString();
		}
    	
      Notification notification  = Notification.builder()
        .setTitle(fcm.title())
      	.setBody(fcm.body().toString())
		.setImage(image)
          .build();
          Message msg = Message.builder()
                  .setNotification(notification)
                  .setTopic(fcm.target())                  
                  .build();
          try {
            FirebaseMessaging.getInstance().send(msg);
          } catch (FirebaseMessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
      }

}
