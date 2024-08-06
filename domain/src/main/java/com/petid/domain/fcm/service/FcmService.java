package com.petid.domain.fcm.service;

import com.petid.domain.fcm.model.Fcm;

/**
 * FCM SERVICE
 *
 * @author : jjm
 * @fileName : PushMessageService
 * @since : 24/07/12
 */
public interface FcmService {

    public void sendNotificationToUser(Fcm fcm) ;
    public void sendNotificationByTopic(Fcm fcm) ;

}