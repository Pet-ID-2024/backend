package com.petid.domain.hospital.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.petid.domain.fcm.model.Fcm;
import com.petid.domain.fcm.service.FcmService;
import com.petid.domain.hospital.model.HospitalOrderSummaryDTO;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalOrderScheduler {

    private final HospitalOrderRepository hospitalOrderRepository;
    private final FcmService fcmService;
    private final MemberManager memberManager;

    @Transactional
    @Scheduled(cron = "0 * 9-19 * * *")
    @Scheduled(cron = "0  0-29 19 * * *") 
    public void processConfirmedOrders() {
    	System.out.println("Order Scheduler called");
        List<HospitalOrderSummaryDTO> confirmedOrders = hospitalOrderRepository.findAllByStatus(OrderStatus.CONFIRMED);
        
        for (HospitalOrderSummaryDTO order : confirmedOrders) {
        	Instant now = Instant.now();

            // Check if current time is one hour before the order date or one hour after
            boolean isWithinOneHourBefore = now.isAfter(order.date().minus(1, ChronoUnit.HOURS));
            boolean isWithinOneHourAfter = now.isAfter(order.date().plus(1, ChronoUnit.HOURS));

            // Process each confirmed order
        	if(isWithinOneHourBefore || isWithinOneHourAfter) {
        		sendOrderNotification(order);
        	}        	
        }
    }
    @Scheduled(cron = "0 0 14 * * *")
    public void processPetUnregisteredMembers() {
    	
    }
    @Transactional
    private void sendOrderNotification(HospitalOrderSummaryDTO order) {
    	System.out.println("Processing order ID: " + order.id());
    	Member member = memberManager.get(order.memberId());
    	if (member.fcmToken() == null) return; 
    	Map<String, Object> body = new HashMap<>();
    	body.put("status", order.status());
		body.put("hostpitalName", order.hospitalName());
    	Fcm fcm = new Fcm("Booking Info", body, member.fcmToken(), null);
    	fcm.updateFcmToken(member.fcmToken());
    	fcmService.sendNotificationToUser(fcm);
    	if(order.status().equals(OrderStatus.CONFIRMED)){
    		hospitalOrderRepository.updateOrderStatus(order.id(), OrderStatus.COMPLETED);
    	}
    }
}
