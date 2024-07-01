package com.petid.batch.component;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.domain.location.SigunguManager;
import com.petid.domain.location.model.Sigungu;
import com.petid.domain.location.repository.EupmundongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataBatch {

    private final DataComponent dataComponent;
    private final EupmundongRepository eupmundongRepository;
    private final SigunguManager sigunguManager;
    private final HospitalRepository hospitalRepository;

//    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateHospitalData() {
        eupmundongRepository.findAll()
                .forEach(eupmundong -> {
                    Sigungu sigungu = sigunguManager.get(eupmundong.sigunguId());

                    Optional.ofNullable(dataComponent.getHospitalData(sigungu.name() + " " + eupmundong.name()))
                            .ifPresent(hospitalDataList -> {
                                List<Hospital> hospitals = hospitalDataList.stream()
                                        .map(data -> data.toDomain(sigungu.sidoId(), sigungu.id(), eupmundong.id()))
                                        .toList();
                                hospitalRepository.saveAllBulk(hospitals);
                            });
                });
    }
}
