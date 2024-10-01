package com.petid.domain.hospital.service;

import com.petid.domain.hospital.manager.HospitalOrderManager;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import com.petid.domain.hospital.type.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    public List<HospitalOrder> getOrders(
        OrderStatus status
    ) {
        return  hospitalOrderRepository.findAllByStatus(status);
    }
    
    @Transactional
    public int updateOrderStatus(long orderId , OrderStatus  status) {
    	return hospitalOrderRepository.updateOrderStatus(orderId,  status);
    }
}
