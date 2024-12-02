package com.petid.api.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petid.api.config.DbSetup;
import com.petid.api.config.PetIdAPITestConfig;
import com.petid.api.member.dto.MemberAuthDto;
import com.petid.api.member.dto.MemberInfoDto;
import com.petid.auth.jwt.TokenValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.petid.api.config.PetIdAPITestConfig.TEST_TOKEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@DbSetup
@SpringBootTest
@AutoConfigureMockMvc
@Import(PetIdAPITestConfig.class)
@DisplayName("Member Controller")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Autowired
    private TokenValidator tokenValidator;

    @BeforeEach
    void setUp() {
        given(tokenValidator.isTokenNotValid(TEST_TOKEN)).willReturn(false);
        given(tokenValidator.getMemberIdFromToken(TEST_TOKEN)).willReturn(1L);
    }

    @Test
    @DisplayName("[Member] Member 정보 조회")
    void getMemberInfo() throws Exception {
        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(get("/v1/member")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .characterEncoding("UTF-8")
                            .contentType(APPLICATION_JSON)
                    )
                    .andReturn();
            result.getResponse().setCharacterEncoding("UTF-8");

            // then
            MemberInfoDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), MemberInfoDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.memberId()).isEqualTo(1L);
            assertThat(response.name()).isEqualTo("친절한 푸들");
            assertThat(response.address()).isNull();
        }
    }

    @Test
    @DisplayName("[Member] Member 정보 등록 여부 조회")
    void isMemberAuth() throws Exception {
        // 1. 정상 요청(정보 미등록)
        {
            // when
            MvcResult result = mockMvc.perform(get("/v1/member/auth")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                    )
                    .andReturn();

            // then
            boolean response = objectMapper.readValue(result.getResponse().getContentAsString(), boolean.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isFalse();
        }
        // 2. 정상 요청(정보 등록)
        {
            // given
            given(tokenValidator.getMemberIdFromToken(TEST_TOKEN)).willReturn(2L);

            // when
            MvcResult result = mockMvc.perform(get("/v1/member/auth")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                    )
                    .andReturn();

            // then
            boolean response = objectMapper.readValue(result.getResponse().getContentAsString(), boolean.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isTrue();
        }
    }

    @Test
    @DisplayName("[Member] Member 정보 업데이트")
    void updateMemberAuth() throws Exception {
        // given
        String newAddress = "new address";
        String newAddressDetails = "new address details";
        String newPhone = "010-9999-9999";
        MemberAuthDto.Request requestBody = new MemberAuthDto.Request(
                newAddress,
                newAddressDetails,
                newPhone

        );

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/member/auth")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody))
                    )
                    .andReturn();
            result.getResponse().setCharacterEncoding("UTF-8");

            // then
            MemberAuthDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), MemberAuthDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.name()).isEqualTo("친절한 푸들");
            assertThat(response.address()).isEqualTo(newAddress);
            assertThat(response.addressDetails()).isEqualTo(newAddressDetails);
            assertThat(response.phone()).isEqualTo(newPhone);
        }
        // 2. null 필드 무시
        {
            // given
            MemberAuthDto.Request nullRequestBody = new MemberAuthDto.Request(
                    null,
                    newAddressDetails,
                    null
            );

            // when
            MvcResult result = mockMvc.perform(post("/v1/member/auth")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(nullRequestBody))
                    )
                    .andReturn();
            result.getResponse().setCharacterEncoding("UTF-8");

            // then
            MemberAuthDto.Response response = objectMapper.readValue(result.getResponse().getContentAsString(), MemberAuthDto.Response.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response.name()).isEqualTo("친절한 푸들");
            assertThat(response.address()).isEqualTo(newAddress);
            assertThat(response.addressDetails()).isEqualTo(newAddressDetails);
            assertThat(response.phone()).isEqualTo(newPhone);
        }
    }

    @Test
    @DisplayName("[Member] Member 광고성 정보 수신 동의 업데이트")
    void updateOptionalPolicy() throws Exception {
        // 1. true로 변경
        {
            // when
            MvcResult result = mockMvc.perform(patch("/v1/member/policy")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .param("ad", "true")
                            .contentType(APPLICATION_JSON)
                    )
                    .andReturn();

            // then
            boolean response = objectMapper.readValue(result.getResponse().getContentAsString(), boolean.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isTrue();
        }
        // 2. false로 변경
        {
            // when
            MvcResult result = mockMvc.perform(patch("/v1/member/policy")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .param("ad", "false")
                            .contentType(APPLICATION_JSON)
                    )
                    .andReturn();

            // then
            boolean response = objectMapper.readValue(result.getResponse().getContentAsString(), boolean.class);

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isFalse();
        }
    }

    @Test
    @DisplayName("[Member] Member 프로필 이미지 경로 수정")
    void updateMemberProfileImage() throws Exception {
        // given
        String newFilePath = "newFilePath.png";

        // 1. 정상 요청
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/member/image")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(newFilePath)
                    )
                    .andReturn();
            result.getResponse().setCharacterEncoding("UTF-8");

            // then
            String response = result.getResponse().getContentAsString();

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response).isEqualTo(newFilePath);
        }
        // 2. Null 전달 시 400
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/member/image")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content("")
                    )
                    .andReturn();

            // then
            assertThat(result.getResponse().getStatus()).isEqualTo(400);
        }
        // 3. 공백 전달 시 무시
        {
            // when
            MvcResult result = mockMvc.perform(post("/v1/member/image")
                            .header(AUTHORIZATION, TEST_TOKEN)
                            .contentType(APPLICATION_JSON)
                            .content(" ")
                    )
                    .andReturn();

            // then
            String response = result.getResponse().getContentAsString();

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            assertThat(response).isNotNull();
            assertThat(response).isEqualTo(newFilePath);
        }
    }
}