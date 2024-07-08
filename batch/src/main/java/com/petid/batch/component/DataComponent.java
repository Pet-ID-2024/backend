package com.petid.batch.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.batch.exception.DataParseException;
import com.petid.batch.model.HospitalItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataComponent {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    @Value("${openapi.service-key}")
    private String serviceKey;

    private static final String OPEN_API_URI = "http://apis.data.go.kr/1543061/recordAgencySrvc/recordAgency";

    public List<HospitalItemModel> getHospitalData(
            String addr
    ) {
        String requestUri = UriComponentsBuilder.fromHttpUrl(OPEN_API_URI)
                .queryParam("serviceKey", serviceKey)
                .queryParam("_type", "json")
                .queryParam("addr", addr)
                .build(false)
                .toUriString();

        String response = restTemplate.getForObject(requestUri, String.class);

        try {
            JsonNode itemNode = objectMapper.readTree(response)
                    .path("response")
                    .path("body")
                    .path("items")
                    .path("item");

            return objectMapper.convertValue(
                    itemNode,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, HospitalItemModel.class));
        } catch (Exception e) {
            log.error("동물등록 대행기관 응답 데이터를 파싱하는 중 오류가 발생했습니다.");
            throw new DataParseException(e);
        }
    }
}
