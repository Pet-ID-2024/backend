package com.petid.api.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.api.pet.config.DbSetup;
import com.petid.api.pet.dto.PetIdDto;
import com.petid.api.pet.dto.PetIdProposerDto;
import com.petid.auth.jwt.TokenProvider;
import com.petid.auth.jwt.TokenValidator;
import com.petid.auth.oauth.sdk.controller.OAuth2UserInfoUriConverter;
import com.petid.domain.config.FcmInitializer;
import com.petid.domain.email.EmailService;
import com.petid.domain.pet.model.Pet;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void setup() {
        // testToken 모킹
        given(tokenValidator.isTokenNotValid(TEST_TOKEN)).willReturn(false);
        given(tokenValidator.getMemberIdFromToken(TEST_TOKEN)).willReturn(1L);
    }

    @Test
    @DisplayName("[Pet] Pet ID 발급")
    void createPetId() throws Exception {
        // 1. 정상 요청
        {
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

            // when
            MvcResult result = mockMvc.perform(post("/v1/pet")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andReturn();

            // then
            Pet response = objectMapper.readValue(result.getResponse().getContentAsString(), Pet.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(201);
            assertThat(response).isNotNull();
            assertThat(response.petName()).isEqualTo("testPetName");
            assertThat(response.appearance().breed()).isEqualTo(Breed.BEAGLE);
        }
        // 2.
        {

        }
    }

    @Test
    @DisplayName("[Pet] Pet ID 업데이트")
    void updatePet() {
    }

    @Test
    void updatePetAppearance() {
    }

    @Test
    void addPetImage() {
    }

    @Test
    void getPetById() {
    }

    @Test
    void getAllPets() {
    }

    @Test
    void deletePet() {
    }

    @Test
    void deletePetImage() {
    }

    @Test
    void getPetImageBucketUrl() {
    }

    @Test
    void putPetImageBucketUrl() {
    }

    @Test
    void putPetIdSignBucketUrl() {
    }
}