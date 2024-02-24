package com.kbtg.bootcamp.posttest.lottery.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.service.LotteryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LotteryController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
public class LotteryControllerTest {
    @MockBean
    private LotteryService lotteryService;

    @Autowired
    private LotteryController lotteryController;

    public static final String TICKET = "012345";


    @Test
    @DisplayName("should return 201 and ticket when create success")
    void testCreate() throws Exception {
        // Arrange
        when(lotteryService.createLottery(Mockito.<LotteryRequestDto>any())).thenReturn(new LotteryResponseDto(TICKET));

        Lottery lottery = new Lottery();
        lottery.setAmount(1);
        lottery.setPrice(80);
        lottery.setTicket(TICKET);
        String content = (new ObjectMapper()).writeValueAsString(lottery);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/lotteries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lotteryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"ticket\":\"" + TICKET + "\"}"));
    }


    @Test
    @DisplayName("should return null when no lottery")
    void testRead() throws Exception {
        // Arrange
        when(lotteryService.listAllLotteries()).thenReturn(new LotteryListResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lotteries")
                .contentType(MediaType.APPLICATION_JSON);


        // Act and Assert
        MockMvcBuilders.standaloneSetup(lotteryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"tickets\":null}"));
    }
}
