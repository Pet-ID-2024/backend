package com.petid.api.location;

import com.petid.api.location.dto.SigunguDto;
import com.petid.domain.location.LocationService;
import com.petid.domain.location.Sigungu;
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

    @GetMapping("/sido/{sidoId}")
    public ResponseEntity<List<SigunguDto.Response>> findSigungu(
            @PathVariable Long sidoId
    ) {
        List<Sigungu> sigungus = locationService.findSigungu(sidoId);

        return ResponseEntity.ok(
                        sigungus.stream()
                                .map(SigunguDto.Response::from)
                                .toList()
                );
    }

}
