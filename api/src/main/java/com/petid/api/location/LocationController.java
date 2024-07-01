package com.petid.api.location;

import com.petid.api.location.dto.LocationDto;
import com.petid.domain.location.LocationService;
import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.model.Sido;
import com.petid.domain.location.model.Sigungu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDto.Response>> findSido() {
        List<Sido> sigungus = locationService.findSido();

        return ResponseEntity.ok(
                sigungus.stream()
                        .map(LocationDto.Response::from)
                        .toList()
        );
    }

    @GetMapping("/sido/{sidoId}/sigungu")
    public ResponseEntity<List<LocationDto.Response>> findSigungu(
            @PathVariable int sidoId
    ) {
        List<Sigungu> sigungus = locationService.findSigungu(sidoId);

        return ResponseEntity.ok(
                        sigungus.stream()
                                .map(LocationDto.Response::from)
                                .toList()
                );
    }

    @GetMapping("/sigungu/{sigunguId}/eupmundong")
    public ResponseEntity<List<LocationDto.Response>> findEupmundong(
            @PathVariable int sigunguId
    ) {
        List<Eupmundong> sigungus = locationService.findEupmundong(sigunguId);

        return ResponseEntity.ok(
                sigungus.stream()
                        .map(LocationDto.Response::from)
                        .toList()
        );
    }

}
