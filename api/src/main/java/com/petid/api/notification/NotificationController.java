package com.petid.api.notification;

import com.petid.api.common.RequestUtil;
import com.petid.domain.fcm.model.Fcm;
import com.petid.domain.fcm.service.FcmService;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notification")
public class NotificationController  {

    private final FcmService fcmService;
    
    private final MemberManager memberManager;

    @PostMapping()
    public ResponseEntity<String> sendNotification(HttpServletRequest request, @RequestBody Fcm fcm) {
    	
    		String targetType = fcm.targetType();
    		if (targetType.equals("TOPIC")) {
    			fcmService.sendNotificationByTopic(fcm);
    		}else if (targetType.equals("USER")) {
    			long memberId = RequestUtil.getMemberIdFromRequest(request);
    			Member member = memberManager.get(memberId);
    			fcm.updateFcmToken(member.fcmToken());
    			fcmService.sendNotificationToUser(fcm);
    		}
            return ResponseEntity.ok("Notification has been sent.");
        
    }

	@PostMapping("/booking")
    public ResponseEntity<String> sendBookingNotification(@RequestBody HospitalOrder hospitalOrder ) {
			String bookingCode = hospitalOrder.status().toString();
			long memberId = hospitalOrder.memberId();
			Member member = memberManager.get(memberId);
			String token = member.fcmToken();
			Fcm fcm = new Fcm("Booking Info", bookingCode , null, token, null);
			fcmService.sendNotificationToUser(fcm);
    		return ResponseEntity.ok("Notification has been sent.");
    }
}
