package com.petid.api.hospital;

import com.petid.api.hospital.dto.HospitalDto;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<HospitalDto.Response>> findAllHospital(
            @RequestParam("sido") int sidoId,
            @RequestParam("sigungu") int sigunguId,
            @RequestParam("eupmundong") List<Integer> eupmundongIds
    ) {
        List<Hospital> hospitals = hospitalService.findAllHospital(sidoId, sigunguId, eupmundongIds);

        return ResponseEntity.ok(
                hospitals.stream()
                        .map(HospitalDto.Response::from)
                        .toList()
        );
    }

}
