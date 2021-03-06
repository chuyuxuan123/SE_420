package com.example.demo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class SpringActuatorTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void springActuatorTestHealth() throws Exception {
        mvc
                .perform(get("/actuator/health"))
                .andDo(print())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    public void springActuatorTestInfo() throws Exception {
        mvc
                .perform(get("/actuator/info"))
                .andExpect(status().isOk());
    }

    @Test
    public void springActuatorTestBeans() throws Exception {
        mvc
                .perform(get("/actuator/beans"))
                .andDo(print());
    }

    @Test
    public void springActuatorTestCaches() throws Exception {
        mvc
                .perform(get("/actuator/caches"))
                .andExpect(jsonPath("$.cacheManagers").value(""));
    }

    @Test
    public void springActuatorTestEnv() throws Exception {
        mvc
                .perform(get("/actuator/env"))
                .andDo(print());
    }
}
