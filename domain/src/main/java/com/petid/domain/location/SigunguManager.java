package com.petid.domain.location;

import com.petid.domain.exception.LocationDataNotFoundException;
import com.petid.domain.location.model.Sigungu;
import com.petid.domain.location.repository.SigunguRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SigunguManager {

    private final SigunguRepository sigunguRepository;


    public Sigungu get(
            long id
    ) {
        return sigunguRepository.findById(id)
                .orElseThrow(() -> new LocationDataNotFoundException(id));
    }
}
