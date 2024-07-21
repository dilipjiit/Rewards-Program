

//RewardController.java
package com.example.rewardprogram.controller;

import com.example.rewardprogram.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

 @Autowired
 private RewardService rewardService;

 @GetMapping("/monthly/{customerId}")
 public Map<Month, Integer> getMonthlyRewards(@PathVariable Long customerId) {
     return rewardService.calculateMonthlyPoints(customerId);
 }

 @GetMapping("/total/{customerId}")
 public int getTotalRewards(@PathVariable Long customerId) {
     return rewardService.calculateTotalPoints(customerId);
 }
}