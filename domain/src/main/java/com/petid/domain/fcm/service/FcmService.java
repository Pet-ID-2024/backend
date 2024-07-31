package com.petid.domain.fcm.service;


import com.google.firebase.messaging.FirebaseMessagingException;
import com.petid.domain.fcm.model.Fcm;

/**
 * FCM SERVICE
 *
 * @author : jjm
 * @fileName : PushMessageService
 * @since : 24/07/12
 */
public interface FcmService {

    public void sendNotificationToUser(Fcm fcm) throws FirebaseMessagingException;
    public void sendNotificationByTopic(Fcm fcm) throws FirebaseMessagingException;

}