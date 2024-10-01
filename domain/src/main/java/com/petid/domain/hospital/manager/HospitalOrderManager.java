package com.petid.domain.hospital.manager;

import com.petid.domain.exception.HospitalNotFoundException;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.repository.HospitalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HospitalOrderManager {

    private final HospitalOrderRepository hospitalOrderRepository;

    public HospitalOrder get(Long id) {
        return hospitalOrderRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
    }

    public Set<LocalTime> getUnavailableTimes(
            long hospitalId,
            LocalDate date
    ) {
        ZoneId zoneId = ZoneId.of("UTC");
        List<HospitalOrder> orderData = hospitalOrderRepository.findAllByHospitalIdAndDateAndStatusValid(hospitalId, date, zoneId);

        return orderData.stream()
                .map(order -> order.date().atZone(zoneId).toLocalTime())
                .collect(Collectors.toSet());
    }
}
