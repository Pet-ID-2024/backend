package com.petid.api.hospital;

import com.petid.api.common.RequestUtil;
import com.petid.api.hospital.dto.CancelHospitalOrderDto;
import com.petid.api.hospital.dto.HospitalOrderDto;
import com.petid.api.hospital.dto.UpdateHospitalOrderDto;
import com.petid.domain.email.EmailService;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.service.HospitalOrderService;
import com.petid.domain.hospital.type.OrderStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital/order")
public class HospitalOrderController {
	
	@Value("${spring.mail.username}")
	private String emailSenderAddr;
	

    private final HospitalOrderService hospitalOrderService;

    private final EmailService emailService; 

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
        long memberId;
        if (request.getParameter("id") != null) {
        	memberId = Long.parseLong(request.getParameter("id"));
        }else {
        	memberId = RequestUtil.getMemberIdFromRequest(request);
        }

        HospitalOrder hospitalOrder = hospitalOrderService.createOrder(orderRequest.toDomain(memberId));

        String recipientEmail = emailSenderAddr; // for unforeseeable future, when we absolutely need to send out emails for booking confirmation,  use "member.email();"
        String subject = "Booking Confirmation";
        String text = "새 병원 예약 요청이 들어왔습니다.";
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
