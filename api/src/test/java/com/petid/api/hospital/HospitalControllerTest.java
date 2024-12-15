package com.petid.api.hospital;

import com.petid.api.config.DbSetup;
import com.petid.api.config.PetIdAPITestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DbSetup
@SpringBootTest
@AutoConfigureMockMvc
@Import(PetIdAPITestConfig.class)
@DisplayName("Hospital Controller")
class HospitalControllerTest {

    @Test
    void findAllHospital() {
    }

    @Test
    void findHospital() {
    }

    @Test
    void findAllHospitalOrderByLocation() {
    }

    @Test
    void isOffDay() {
    }

    @Test
    void getHospitalImageBucketUrl() {
    }
}