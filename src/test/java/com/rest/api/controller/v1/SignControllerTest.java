package com.rest.api.controller.v1;

import com.rest.api.entity.User;
import com.rest.api.repo.UserJpaRepo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        userJpaRepo.save(User.builder().uid("happydaddy@naver.com").name("happydaddy").password(passwordEncoder.encode("1234")).roles(Collections.singletonList("ROLE_USER")).build());
    }

    @Test
    public void signin() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "happydaddy@naver.com");
        params.add("password", "1234");
        mockMvc.perform(post("/v1/signin").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void signinFail() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "happydaddy@naver.com");
        params.add("password", "12345");
        mockMvc.perform(post("/v1/signin").params(params))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-1001))
                .andExpect(jsonPath("$.msg").exists());
    }

    @Test
    public void signup() throws Exception {
        long epochTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "happydaddy_" + epochTime + "@naver.com");
        params.add("password", "12345");
        params.add("name", "happydaddy_" + epochTime);
        mockMvc.perform(post("/v1/signup").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists());
    }

    @Test
    public void signupFail() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "happydaddy@naver.com");
        params.add("password", "12345");
        params.add("name", "happydaddy");
        mockMvc.perform(post("/v1/signup").params(params))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-9999));
    }

    @Test
    public void signInProviderFail() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accessToken", "XXXXXXXX");
        mockMvc.perform(post("/v1/signin/kakao").params(params))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-1004));
    }

    @Test @Ignore
    public void signUpSocial() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accessToken", "HizF3ir9522bMW3shkO0x0T9zBdXFCW1WsF56Qo9dVsAAAFqMwTqHw");
        params.add("name", "kakaoKing!");
        mockMvc.perform(post("/v1/signup/kakao").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test @Ignore
    public void signInSocial() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accessToken", "HizF3ir9522bMW3shkO0x0T9zBdXFCW1WsF56Qo9dVsAAAFqMwTqHw");
        mockMvc.perform(post("/v1/signin/kakao").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").exists());
    }
}