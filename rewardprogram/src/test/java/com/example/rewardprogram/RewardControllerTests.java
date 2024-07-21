

//RewardControllerTests.java
package com.example.rewardprogram;

import com.example.rewardprogram.controller.RewardController;
import com.example.rewardprogram.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Month;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RewardControllerTests {

 @Mock
 private RewardService rewardService;

 @InjectMocks
 private RewardController rewardController;

 private MockMvc mockMvc;

 @Test
 public void testGetMonthlyRewards() throws Exception {
     mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();

     when(rewardService.calculateMonthlyPoints(1L)).thenReturn(Map.of(Month.JANUARY, 110, Month.FEBRUARY, 40));

     mockMvc.perform(get("/api/rewards/monthly/1")
             .accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(content().json("{\"JANUARY\":110,\"FEBRUARY\":40}"));
 }

 @Test
 public void testGetTotalRewards() throws Exception {
     mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();

     when(rewardService.calculateTotalPoints(1L)).thenReturn(150);

     mockMvc.perform(get("/api/rewards/total/1")
             .accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(content().string("150"));
 }
}
