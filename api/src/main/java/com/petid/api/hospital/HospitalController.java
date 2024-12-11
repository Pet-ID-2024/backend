package com.petid.api.hospital;

import com.petid.api.hospital.dto.HospitalDto;
import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.service.HospitalService;
import com.petid.domain.hospital.type.DayType;
import com.petid.domain.pet.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hospital")
public class HospitalController {

    private final HospitalService hospitalService;
    private final S3Service s3Service;

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

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalDto.Response> findHospital(
            @PathVariable long hospitalId
    ) {
        Hospital hospitals = hospitalService.findHospitalById(hospitalId);

        return ResponseEntity.ok(
                HospitalDto.Response.from(hospitals)
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

    @GetMapping("/images/presigned-url")
    public ResponseEntity<String> getHospitalImageBucketUrl(
            @RequestParam String filePath
    ) {
        String url = s3Service.createPresignedGetUrl(filePath);
        return ResponseEntity.ok(url);
    }
}
