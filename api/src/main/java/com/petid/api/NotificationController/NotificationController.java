package com.petid.api.NotificationController;

import com.petid.api.common.RequestUtil;
import com.petid.domain.fcm.model.Fcm;
import com.petid.domain.fcm.service.FcmService;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationController  {

    private final FcmService fcmService;
    
    private final MemberService memberService;

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(HttpServletRequest request, @RequestBody Fcm fcm) {
    	
    		String targetType = fcm.targetType();
    		if (targetType.equals("TOPIC")) {
    			fcmService.sendNotificationByTopic(fcm);
    		}else if (targetType.equals("USER")) {
    			String uid = RequestUtil.getUidFromRequest(request);
    			Member member = memberService.getUserByUid(uid);
    			fcm.updateFcmToken(member.fcmToken());
    			fcmService.sendNotificationToUser(fcm);
    		}
            return ResponseEntity.ok("Notification has been sent.");
        
    }
}
