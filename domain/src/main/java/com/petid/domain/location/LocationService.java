package com.petid.domain.location;

import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.model.Sido;
import com.petid.domain.location.model.Sigungu;
import com.petid.domain.location.repository.EupmundongRepository;
import com.petid.domain.location.repository.SidoRepository;
import com.petid.domain.location.repository.SigunguRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final SidoRepository sidoRepository;
    private final SigunguRepository sigunguRepository;
    private final EupmundongRepository eupmundongRepository;

    public List<Sigungu> findSigungu(
            int sidoId
    ) {
        return sigunguRepository.findAllBySidoId(sidoId);
    }

    public List<Eupmundong> findEupmundong(
            int sigunguId
    ) {
        return eupmundongRepository.findAllBySigunguId(sigunguId);
    }

    public List<Sido> findSido() {
        return sidoRepository.findAll();
    }
}
