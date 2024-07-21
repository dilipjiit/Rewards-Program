

//CustomerRepository.java
package com.example.rewardprogram.repository;

import com.example.rewardprogram.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

//TransactionRepository.java
package com.example.rewardprogram.repository;

import com.example.rewardprogram.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
 List<Transaction> findByCustomerId(Long customerId);
}
