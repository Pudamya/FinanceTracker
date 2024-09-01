package com.example.financetracker.repository;

import com.example.financetracker.models.Transaction;
import com.example.financetracker.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    List<Transaction> findTop10ByOrderByTransactionDateDesc();

    List<Transaction> findByTransactionDateAfterAndTypeAndCategoryId(LocalDate startDate, TransactionType type, Long categoryId);

    List<Transaction> findByTransactionDateBetweenAndTypeAndCategoryId(LocalDate startDate, LocalDate endDate, TransactionType type, Long categoryId);

    List<Transaction> findByTransactionDateBeforeAndTypeAndCategoryId(LocalDate endDate, TransactionType type, Long categoryId);

    List<Transaction> findByTypeAndCategoryId(TransactionType type, Long categoryId);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByCategoryId(Long categoryId);

    List<Transaction> findByTransactionDateBefore(LocalDate endDate);

    List<Transaction> findByTransactionDateBeforeAndType(LocalDate endDate, TransactionType type);

    List<Transaction> findByTransactionDateAfter(LocalDate startDate);

    List<Transaction> findByTransactionDateBetweenAndType(LocalDate startDate, LocalDate endDate, TransactionType type);

    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

    List<Transaction> findByTransactionDateAfterAndType(LocalDate startDate, TransactionType type);
}
