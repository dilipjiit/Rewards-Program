// RewardServiceTests.java
package com.example.rewardprogram;

import com.example.rewardprogram.model.Transaction;
import com.example.rewardprogram.repository.TransactionRepository;
import com.example.rewardprogram.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardService rewardService;

    @Test
    public void testCalculateMonthlyPoints() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, LocalDate.of(2023, Month.JANUARY, 15), 120, null),
                new Transaction(2L, LocalDate.of(2023, Month.JANUARY, 20), 80, null),
                new Transaction(3L, LocalDate.of(2023, Month.FEBRUARY, 10), 90, null)
        );

        when(transactionRepository.findByCustomerId(1L)).thenReturn(transactions);

        Map<Month, Integer> points = rewardService.calculateMonthlyPoints(1L);
        assertEquals(110, points.get(Month.JANUARY));
        assertEquals(40, points.get(Month.FEBRUARY));
    }

    @Test
    public void testCalculateTotalPoints() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, LocalDate.of(2023, Month.JANUARY, 15), 120, null),
                new Transaction(2L, LocalDate.of(2023, Month.JANUARY, 20), 80, null),
                new Transaction(3L, LocalDate.of(2023, Month.FEBRUARY, 10), 90, null)
        );

        when(transactionRepository.findByCustomerId(1L)).thenReturn(transactions);

        int totalPoints = rewardService.calculateTotalPoints(1L);
        assertEquals(150, totalPoints);
    }
}
