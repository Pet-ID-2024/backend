package com.petid.domain.hospital.manager;

import com.petid.domain.exception.HospitalNotFoundException;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalManager {

    private final HospitalRepository hospitalRepository;

    public Hospital get(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
    }
}
