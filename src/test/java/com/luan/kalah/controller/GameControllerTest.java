package com.luan.kalah.controller;

import com.luan.kalah.model.GameResponse;
import com.luan.kalah.model.StatusResponse;
import com.luan.kalah.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GameControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private GameService gameService;
    @Value("${server.port}")
    private String serverPort;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void performCreateGameEndpoint() throws Exception {
        gameService.resetGame();
        MvcResult mvcResult = mockMvc.perform(post("/games"))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        GameResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(), GameResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(1L, response.getId());
        Assert.assertEquals("http://localhost:"+serverPort+"/games/1", response.getUri());
    }

    @Test
    public void performMoveEndpoint() throws Exception {
        gameService.resetGame();
        gameService.createGame();

        MvcResult mvcResult = mockMvc.perform(put("/games/1/pits/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        StatusResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(), StatusResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(1L, response.getId());
        Assert.assertEquals("http://localhost:"+serverPort+"/games/1", response.getUri());
        String status = "{\"1\":0,\"2\":7,\"3\":7,\"4\":7,\"5\":7,\"6\":7,\"7\":1,\"8\":6,\"9\":6,\"10\":6,\"11\":6,\"12\":6,\"13\":6,\"14\":0}";
        Assert.assertEquals(status, response.getStatus());
    }



}