

//RewardService.java
package com.example.rewardprogram.service;

import com.example.rewardprogram.model.Transaction;
import com.example.rewardprogram.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

 @Autowired
 private TransactionRepository transactionRepository;

 public Map<Month, Integer> calculateMonthlyPoints(Long customerId) {
     List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

     return transactions.stream()
             .collect(Collectors.groupingBy(
                     t -> t.getDate().getMonth(),
                     Collectors.summingInt(this::calculatePoints)
             ));
 }

 public int calculateTotalPoints(Long customerId) {
     List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
     return transactions.stream()
             .mapToInt(this::calculatePoints)
             .sum();
 }

 private int calculatePoints(Transaction transaction) {
     double amount = transaction.getAmount();
     int points = 0;

     if (amount > 100) {
         points += (amount - 100) * 2;
         amount = 100;
     }
     if (amount > 50) {
         points += (amount - 50);
     }

     return points;
 }
}
