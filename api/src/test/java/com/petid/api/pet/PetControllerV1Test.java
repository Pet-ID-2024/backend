package com.petid.api.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.api.pet.config.DbSetup;
import com.petid.domain.config.FcmInitializer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DbSetup
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pet Controller")
class PetControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private FcmInitializer fcmInitializer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("[Pet] Pet ID 발급")
    void createPetId() throws Exception {
        // 1. 정상 요청
        {
            // body

            mockMvc.perform(MockMvcRequestBuilders.post("/v1/pet"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, World!"));
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