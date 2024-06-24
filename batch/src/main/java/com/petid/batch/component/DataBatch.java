package com.petid.batch.component;

import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.domain.location.SigunguRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBatch {

    private final DataComponent dataComponent;
    private final SigunguRepository sigunguRepository;
    private final HospitalRepository hospitalRepository;

//    @Scheduled(cron = "0 0 0 1 * ?")
//    public void updateHospitalData() {
//        int sidoId = 1;
//        sigunguRepository.findAllBySidoId(sidoId)
//                .forEach(sigungu -> {
//                    List<Hospital> hospitals = dataComponent.getHospitalData(sigungu.name())
//                            .stream()
//                            .map(data -> data.toDomain(sidoId))
//                            .toList();
//                    hospitalRepository.saveAllBulk(hospitals);
//                });
//    }
}
