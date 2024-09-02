package com.petid.api.hospital;

import com.petid.api.common.RequestUtil;
import com.petid.api.hospital.dto.CancelHospitalOrderDto;
import com.petid.api.hospital.dto.HospitalOrderDto;
import com.petid.api.hospital.dto.UpdateHospitalOrderDto;
import com.petid.domain.email.EmailService;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.service.HospitalOrderService;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.model.Member;
import com.petid.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital/order")
public class HospitalOrderController {

    private final HospitalOrderService hospitalOrderService;

    private final EmailService emailService; 

    private final MemberService memberService; 

    @GetMapping
    public List<HospitalOrder> findAllOrder(@RequestParam("status") OrderStatus status) {
        // TODO 조회 페이지 기획 필요 - 페이징 적용 가능성

        return hospitalOrderService.getOrders(status);

    }

    @PostMapping
    public ResponseEntity<HospitalOrderDto.Response> createOrder(
            HttpServletRequest request,
            @RequestBody HospitalOrderDto.Request orderRequest
    ) {
        String uid = RequestUtil.getUidFromRequest(request);

        HospitalOrder hospitalOrder = hospitalOrderService.createOrder(orderRequest.toDomain(uid));

        Member member = memberService.getUserByUid(uid);
        String recipientEmail = member.email(); 
        String subject = "Booking Confirmation";
        String text = "your appointment has been scheduled. We will get back to you as soon as possible.";
        emailService.sendEmail(recipientEmail, subject, text);

        return ResponseEntity.ok(
                HospitalOrderDto.Response.from(hospitalOrder)
        );
    }

    @PatchMapping
    public ResponseEntity<HospitalOrderDto.Response> updateOrder(
            @RequestBody UpdateHospitalOrderDto.Request updateRequest
    ) {
        HospitalOrder hospitalOrder = hospitalOrderService.updateOrder(
                updateRequest.orderId(),
                updateRequest.date()
        );

        return ResponseEntity.ok(
                HospitalOrderDto.Response.from(hospitalOrder)
        );
    }

    @DeleteMapping
    public ResponseEntity<Long> cancelOrder(
            @RequestBody CancelHospitalOrderDto.Request cancelRequest
    ) {
        long requestOrderId = cancelRequest.orderId();
        hospitalOrderService.cabcekOrder(requestOrderId);

        return ResponseEntity.ok(requestOrderId);
    }
}
