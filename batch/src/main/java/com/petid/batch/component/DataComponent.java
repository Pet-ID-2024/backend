package com.petid.batch.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.batch.exception.DataParseException;
import com.petid.batch.model.HospitalItemModel;
import com.petid.batch.model.LocationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoKey;

    private static final String HOSPITAL_OPEN_API_URI = "http://apis.data.go.kr/1543061/recordAgencySrvc/recordAgency";
    private static final String LOCATION_OPEN_API_URI = "https://dapi.kakao.com/v2/local/search/address.json";

    public List<HospitalItemModel> getHospitalData(
            String addr
    ) {
        String requestUri = UriComponentsBuilder.fromHttpUrl(HOSPITAL_OPEN_API_URI)
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

    public LocationModel getLocationData(
            String fullAddr
    ) {
        String requestUri = UriComponentsBuilder.fromHttpUrl(LOCATION_OPEN_API_URI)
                .queryParam("query", fullAddr)
                .build(false)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String response = restTemplate.exchange(requestUri, HttpMethod.GET, entity, String.class).getBody();

        try {
            JsonNode itemNode = objectMapper.readTree(response)
                    .path("documents")
                    .get(0);

            return (itemNode == null)
                    ? new LocationModel(null,null, null, null, null, null)
                    : objectMapper.convertValue(itemNode, LocationModel.class);
        } catch (Exception e) {
            log.error("X Y 좌표 데이터를 파싱하는 중 오류가 발생했습니다.");
            throw new DataParseException(e);
        }
    }
}
