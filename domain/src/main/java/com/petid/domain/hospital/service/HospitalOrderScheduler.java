package com.petid.domain.hospital.service;

import com.petid.domain.fcm.model.Fcm;
import com.petid.domain.fcm.service.FcmService;
import com.petid.domain.hospital.model.HospitalOrderSummaryDTO;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.manager.MemberManager;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class HospitalOrderScheduler {

    private final HospitalOrderRepository hospitalOrderRepository;
    private final MemberRepository memberRepository;
    private final FcmService fcmService;
    private final MemberManager memberManager;

    @Transactional
    @Scheduled(cron = "0 * 9-19 * * *")
    @Scheduled(cron = "0  0-29 19 * * *") 
    public void processConfirmedOrders() {
    	log.info("Order Scheduler called");
        List<HospitalOrderSummaryDTO> confirmedOrders = hospitalOrderRepository.findAllByStatus(null, OrderStatus.CONFIRMED);
        
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
    	List<Member> membersWithouPets = memberRepository.findMembersWithoutPets();
    	for (Member member : membersWithouPets) {
    		sendPetRegisterReminderNotification(member);
    	}
    	
    }
    @Transactional
    protected void sendOrderNotification(HospitalOrderSummaryDTO order) {
		log.info("Processing order ID: " + order.id());
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
    
    @Transactional
    protected void sendPetRegisterReminderNotification(Member member) {
		log.info("Processing member ID: " + member.id());
    	if (member.fcmToken() == null) return; 
    	Map<String, Object> body = new HashMap<>();
    	body.put("id", member.id() );
    	body.put("message", "간단한 반려동물등록으로 평생을 함께하는 방법 지금 알려드려요!");		
    	Fcm fcm = new Fcm("PetRegReminder", body, member.fcmToken(), null);
    	fcm.updateFcmToken(member.fcmToken());
    	fcmService.sendNotificationToUser(fcm);    	
    }
}
