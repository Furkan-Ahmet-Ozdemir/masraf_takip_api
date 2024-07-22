package com.example.masraf_takip_api.service;

import com.example.masraf_takip_api.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledJobService {

    private final UserService userService;

    private final TransactionService transactionService;

    @Scheduled(cron = "0 0 5 * * *") // Scheduled to work every day at 5 AM
    public void calculateTransactionsDaily() {
        LocalDate today = LocalDate.now();
        List<Transaction> transactions = transactionService.getTransactionsByDateRange(today.minusDays(1), today);
        log.info(transactions.toString());
    }

    @Scheduled(cron = "0 3 * * 7") // Scheduled to work once every week on Monday
    public void calculateTransactionsWeekly() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<Transaction> transactions = transactionService.getTransactionsByDateRange(startOfWeek.minusWeeks(1), startOfWeek);
        log.info(transactions.toString());
    }

    @Scheduled(cron = "0 3 1 * *") // Scheduled to work once every month
    public void calculateTransactionsMonthly() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        List<Transaction> transactions = transactionService.getTransactionsByDateRange(startOfMonth.minusMonths(1), startOfMonth);
        log.info(transactions.toString());
    }

}
