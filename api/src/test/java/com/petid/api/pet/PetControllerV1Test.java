package com.petid.api.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.api.exception.ExceptionResponse;
import com.petid.api.pet.dto.*;
import com.petid.auth.jwt.TokenProvider;
import com.petid.auth.jwt.TokenValidator;
import com.petid.auth.oauth.sdk.controller.OAuth2UserInfoUriConverter;
import com.petid.domain.config.FcmInitializer;
import com.petid.domain.email.EmailService;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.type.Breed;
import com.petid.domain.type.Chip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@DbSetup
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pet Controller")
class PetControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Autowired
    private TokenValidator tokenValidator;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private OAuth2UserInfoUriConverter oAuth2UserInfoUriConverter;

    @MockBean
    private EmailService emailService;

    @MockBean
    private FcmInitializer fcmInitializer;

    private static final String TEST_TOKEN = "testToken";

    @BeforeEach
    void setUp() {
        given(tokenValidator.isTokenNotValid(TEST_TOKEN)).willReturn(false);
        given(tokenValidator.getMemberIdFromToken(TEST_TOKEN)).willReturn(1L);
    }

    @Test
    @DisplayName("[Pet] Pet ID 발급")
    void createPetId() throws Exception {
        // given
        PetAppearance petAppearance = new PetAppearance(
                null,
                Breed.BEAGLE,
                "Green",
                10,
                "Long"
        );
        PetIdProposerDto petIdProposer = new PetIdProposerDto(
                "name",
                "address",
                "address details",
                "address",
                "address details",
                "010-1111-1111"
        );
        PetIdDto.Request requestBody = new PetIdDto.Request(
                null,
                null,
                "testPetName",
                "2024-10-11",
                'M',
                'Y',
                "2019-07-23 09:00:00",
                "testAddr",
                "testAddrDetails",
                Chip.NA,
                petAppearance,
                Collections.emptyList(),
                petIdProposer,
                "testSign.png"
        );

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/pet")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            PetDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), PetDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(201);
            assertThat(response).isNotNull();
            assertThat(response.petName()).isEqualTo("testPetName");
            assertThat(response.appearance().breed()).isEqualTo(Breed.BEAGLE);
        }
        // 2. Pet 중복 등록 시도
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/pet")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Member ID 1 Pet data already exists");
        }
    }

    @Test
    @DisplayName("[Pet] Pet ID 업데이트")
    void updatePet() throws Exception {
        // given
        String petNeuteredDate = "2024-10-11 09:00:00";
        int weight = 1000;
        PetUpdateDto.PetAppearanceUpdateDto appearanceUpdateDto = new PetUpdateDto.PetAppearanceUpdateDto(weight);
        PetUpdateDto.Request requestBody = new PetUpdateDto.Request(petNeuteredDate, appearanceUpdateDto);

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(put("/v1/pet/2")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            PetDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), PetDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.petNeuteredDate()).isEqualTo(petNeuteredDate);
            assertThat(response.appearance().weight()).isEqualTo(weight);
        }
        // 2. 존재하지 않는 Pet ID
        {
            // given
            int testId = 999;

            // when
            MvcResult result = mockMvc.perform(put("/v1/pet/" + testId)
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet with ID " + testId + " not found");
        }
    }

    @Test
    @DisplayName("[Pet] Pet Appearance 수정")
    void updatePetAppearance() throws Exception {
        // given
        Breed newBreed = Breed.BULLDOG;
        String newHairColor = "Blue";
        int newWeight = 1000;
        String newHairLength = "Short";
        PetAppearanceDto.Request requestBody = new PetAppearanceDto.Request(
                2L,
                newBreed,
                newHairColor,
                newWeight,
                newHairLength
        );

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(put("/v1/pet/2/appearance")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            PetAppearanceDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), PetAppearanceDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.breed()).isEqualTo(newBreed);
            assertThat(response.weight()).isEqualTo(newWeight);
            assertThat(response.hairLength()).isEqualTo(newHairLength);
        }
        // 2. 존재하지 않는 Pet ID
        {
            // given
            int wrongPetId = 999;

            // when
            MvcResult result = mockMvc.perform(put("/v1/pet/" + wrongPetId + "/appearance")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet with ID " + wrongPetId + " not found");
        }
        // 3. 존재하지 않는 Pet Appearance ID
        {
            // given
            int testPetId = 2;
            long wrongAppearanceId = 999L;
            PetAppearanceDto.Request wrongRequestBody = new PetAppearanceDto.Request(
                    wrongAppearanceId,
                    newBreed,
                    newHairColor,
                    newWeight,
                    newHairLength
            );

            // when
            MvcResult result = mockMvc.perform(put("/v1/pet/" + testPetId + "/appearance")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(wrongRequestBody)))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet appearance for pet with ID " + wrongAppearanceId + " not found");
        }
    }

    @Test
    @DisplayName("[Pet] Pet 이미지 추가")
    void addPetImage() throws Exception {
        // given
        long petImageId = 1L;
        long petId = 2L;
        String imagePath = "testImage.png";
        PetImageDto.Request requestBody = new PetImageDto.Request(
                petImageId,
                petId,
                imagePath
        );

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/pet/2/images")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            PetImageDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), PetImageDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(201);
            assertThat(response).isNotNull();
            assertThat(response.petId()).isEqualTo(petId);
            assertThat(response.petImageId()).isEqualTo(petImageId);
            assertThat(response.imagePath()).isEqualTo(imagePath);
        }
        // 2. 존재하지 않는 Pet ID
        {
            // given
            int wrongPetId = 999;

            // when
            MvcResult result = mockMvc.perform(post("/v1/pet/" + wrongPetId + "/images")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet with ID " + wrongPetId + " not found");
        }
    }

    @Test
    @DisplayName("[Pet] Pet ID로 Pet 조회")
    void getPetById() throws Exception {
        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(get("/v1/pet/2")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON))
                    .andReturn();

            // then
            PetDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), PetDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.petId()).isEqualTo(2);
            assertThat(response.ownerId()).isEqualTo(2);
            assertThat(response.petName()).isEqualTo("test1");
            assertThat(response.chipType()).isEqualTo(Chip.NA);
        }
        // 2. 존재하지 않는 Pet ID
        {
            // given
            int wrongPetId = 999;

            // when
            MvcResult result = mockMvc.perform(get("/v1/pet/" + wrongPetId)
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet with ID " + wrongPetId + " not found");
        }
    }

    @Test
    @DisplayName("[Pet] Pet Image 데이터 삭제")
    void deletePetImage() throws Exception {
        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(delete("/v1/pet/2/images/2")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON))
                    .andReturn();

            // then
            assertThat(result.getResponse().getStatus()).isEqualTo(204);
        }
        // 2. 존재하지 않는 Pet ID
        {
            // given
            int wrongPetId = 999;

            // when
            MvcResult result = mockMvc.perform(delete("/v1/pet/" + wrongPetId + "/images/2")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON))
                    .andReturn();

            // then
            ExceptionResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(400);
            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(400);
            assertThat(response.error()).isEqualTo("Pet with ID " + wrongPetId + " not found");
        }
    }
}