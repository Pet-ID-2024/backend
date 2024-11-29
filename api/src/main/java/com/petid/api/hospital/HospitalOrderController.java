package com.petid.api.hospital;

import com.petid.api.common.RequestUtil;
import com.petid.api.hospital.dto.HospitalOrderDto;
import com.petid.api.hospital.dto.UpdateHospitalOrderDto;
import com.petid.domain.email.EmailService;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.service.HospitalHourService;
import com.petid.domain.hospital.service.HospitalOrderService;
import com.petid.domain.hospital.type.DayType;
import com.petid.domain.hospital.type.OrderStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital/order")
public class HospitalOrderController {
	
    private final HospitalOrderService hospitalOrderService;
    private final HospitalHourService hospitalHourService;

    private final EmailService emailService; 

    @GetMapping
    public List<HospitalOrderDto.NameResponse> findAllMemberOrder(
            HttpServletRequest request,
            @RequestParam("status") OrderStatus status
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        return hospitalOrderService.findMemberOrders(memberId, status)
                .stream()
                .map(HospitalOrderDto.NameResponse::from)
                .toList();

    }

    @PostMapping
    public ResponseEntity<HospitalOrderDto.Response> createOrder(
            HttpServletRequest request,
            @RequestBody HospitalOrderDto.Request orderRequest
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        HospitalOrder hospitalOrder = hospitalOrderService.createOrder(orderRequest.toDomain(memberId));

        String subject = "Booking Confirmation";
        String text = "새 병원 예약 요청이 들어왔습니다.";
        emailService.sendEmail(subject, text);

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

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Long> cancelOrder(
            HttpServletRequest request,
            @PathVariable long orderId
    ) {
        long memberId = RequestUtil.getMemberIdFromRequest(request);

        hospitalOrderService.cancelOrder(orderId, memberId);

        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/time")
    public ResponseEntity<List<String>> findAvailableTimes(
            @RequestParam("hospitalId") long hospitalId,
            @RequestParam("day") DayType day,
            @RequestParam("date") LocalDate date
    ) {
        List<String> availableTimes = hospitalHourService.findAvailableTimes(hospitalId, day, date);

        return ResponseEntity.ok(availableTimes);
    }
}
