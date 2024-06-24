package com.petid.domain.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final SigunguRepository sigunguRepository;

    public List<Sigungu> findSigungu(
            long sidoId
    ) {
        return sigunguRepository.findAllBySidoId(sidoId);
    }
}
