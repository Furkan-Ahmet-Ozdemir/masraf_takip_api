package com.example.masraf_takip_api.service;
import com.example.masraf_takip_api.model.Transaction;
import com.example.masraf_takip_api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setName(transactionDetails.getName());
            transaction.setDescription(transactionDetails.getDescription());
            transaction.setCost(transactionDetails.getCost());
            return transactionRepository.save(transaction);
        } else {
            return null;
        }
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Long getTotalSpendingByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream().mapToLong(Transaction::getCost).sum();
    }
}