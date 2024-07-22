package com.example.masraf_takip_api.repository;
import com.example.masraf_takip_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

}