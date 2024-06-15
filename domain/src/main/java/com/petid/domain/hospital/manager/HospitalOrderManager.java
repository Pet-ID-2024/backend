package com.petid.domain.hospital.manager;

import com.petid.domain.exception.HospitalNotFoundException;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalOrderManager {

    private final HospitalOrderRepository hospitalOrderRepository;

    public HospitalOrder get(Long id) {
        return hospitalOrderRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
    }
}
