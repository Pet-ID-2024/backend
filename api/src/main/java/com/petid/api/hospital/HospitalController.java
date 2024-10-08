package com.petid.api.hospital;

import com.petid.api.hospital.dto.HospitalDto;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.service.HospitalService;
import com.petid.domain.hospital.type.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam("eupmundong") List<Long> eupmundongIds
    ) {
        List<Hospital> hospitals = hospitalService.findAllHospital(sidoId, sigunguId, eupmundongIds);

        return ResponseEntity.ok(
                hospitals.stream()
                        .map(HospitalDto.Response::from)
                        .toList()
        );
    }

    @GetMapping("/location")
    public ResponseEntity<List<HospitalDto.Response>> findAllHospitalOrderByLocation(
            @RequestParam("sido") int sidoId,
            @RequestParam("sigungu") int sigunguId,
            @RequestParam("eupmundong") List<Long> eupmundongIds,
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon
    ) {
        List<Hospital> hospitals = hospitalService.findAllHospitalOrderByLocation(sidoId, sigunguId, eupmundongIds, lat, lon);

        return ResponseEntity.ok(
                hospitals.stream()
                        .map(HospitalDto.Response::from)
                        .toList()
        );
    }

    @GetMapping("/off")
    public ResponseEntity<Boolean> isOffDay(
            @RequestParam("hospitalId") long hospitalId,
            @RequestParam("day") DayType day
    ) {
        return ResponseEntity.ok(
                hospitalService.isOffDay(hospitalId, day)
        );
    }
}
