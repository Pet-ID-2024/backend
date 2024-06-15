package com.petid.domain.hospital.service;

import com.petid.domain.hospital.manager.HospitalOrderManager;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class HospitalOrderService {

    private final HospitalOrderManager hospitalOrderManager;
    private final HospitalOrderRepository hospitalOrderRepository;

    public HospitalOrder createOrder(
            HospitalOrder hospitalOrder
    ) {
        return hospitalOrderRepository.save(hospitalOrder);
    }

    public HospitalOrder updateOrder(
            Long orderId,
            Instant date
    ) {
        HospitalOrder order = hospitalOrderManager.get(orderId);

        HospitalOrder updated = order.update(date);

        return hospitalOrderRepository.save(updated);
    }

    public void cabcekOrder(
            long orderId
    ) {
        HospitalOrder order = hospitalOrderManager.get(orderId);

        HospitalOrder deleted = order.cancel();

        hospitalOrderRepository.save(deleted);
    }
}
