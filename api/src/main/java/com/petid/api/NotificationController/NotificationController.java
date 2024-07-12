package com.petid.api.NotificationController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.petid.domain.fcm.model.Fcm;
import com.petid.domain.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationController  {

    private final FcmService fcmService;

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody Fcm fcm) {
    	try {
            fcmService.sendNotification(fcm);
            return ResponseEntity.ok("Notification has been sent.");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending notification: " + e.getMessage());
        }
    }
}
