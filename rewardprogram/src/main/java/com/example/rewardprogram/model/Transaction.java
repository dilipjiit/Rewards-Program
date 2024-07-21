

//Transaction.java
package com.example.rewardprogram.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Transaction {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private LocalDate date;
 private double amount;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "customer_id", nullable = false)
 private Customer customer;
}
