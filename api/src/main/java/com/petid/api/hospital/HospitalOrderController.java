package com.petid.api.hospital;

import com.petid.api.common.RequestUtil;
import com.petid.api.hospital.dto.CancelHospitalOrderDto;
import com.petid.api.hospital.dto.HospitalOrderDto;
import com.petid.api.hospital.dto.UpdateHospitalOrderDto;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.service.HospitalOrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital/order")
public class HospitalOrderController {

    private final HospitalOrderService hospitalOrderService;

    @GetMapping
    public void findAllOrder() {
        // TODO 조회 페이지 기획 필요 - 페이징 적용 가능성
    }

    @PostMapping
    public ResponseEntity<HospitalOrderDto.Response> createOrder(
            HttpServletRequest request,
            @RequestBody HospitalOrderDto.Request orderRequest
    ) {
        String uid = RequestUtil.getUidFromRequest(request);

        HospitalOrder hospitalOrder = hospitalOrderService.createOrder(orderRequest.toDomain(uid));

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
