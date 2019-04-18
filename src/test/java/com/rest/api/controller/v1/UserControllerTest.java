package com.rest.api.controller.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Before
    public void setUp() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "happydaddy@naver.com");
        params.add("password", "1234");
        MvcResult result = mockMvc.perform(post("/v1/signin").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").exists())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        token = jsonParser.parseMap(resultString).get("data").toString();
    }

    @Test
    public void invalidToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/users")
                .header("X-AUTH-TOKEN", "XXXXXXXXXX"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/exception/entrypoint"));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = {"ADMIN"}) // 가상의 Mock 유저 대입
    public void accessdenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/users"))
                //.header("X-AUTH-TOKEN", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/exception/accessdenied"));
    }

    @Test
    public void findAllUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/users")
                .header("X-AUTH-TOKEN", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.list").exists());
    }

    @Test
    public void findUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/user")
                .header("X-AUTH-TOKEN", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void modify() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("msrl", "1");
        params.add("name", "행복전도사");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/user")
                .header("X-AUTH-TOKEN", token)
                .params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/user/2")
                .header("X-AUTH-TOKEN", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}