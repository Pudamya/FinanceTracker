package com.example.financetracker.services;

import com.example.financetracker.models.Category;
import com.example.financetracker.models.Transaction;
import com.example.financetracker.models.TransactionType;
import com.example.financetracker.repository.CategoryRepo;
import com.example.financetracker.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
//    @Autowired
//    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    //
    @Autowired
    private CategoryRepo categoryRepo;
//
//    public void saveTransaction(Transaction transaction) {
//        transactionRepo.save(transaction);
//    }
//
//    public BigDecimal getTotalIncome() {
//        // Logic to calculate total income
//        return BigDecimal.ZERO; // Placeholder
//    }
//
//    public BigDecimal getTotalExpenses() {
//        // Logic to calculate total expenses
//        return BigDecimal.ZERO; // Placeholder
//    }

    public BigDecimal getTotalIncome() {
        return transactionRepo.findByType(TransactionType.INCOME).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return transactionRepo.findByType(TransactionType.EXPENSE).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

//    public List<Transaction> getRecentTransactions() {
//        // Logic to get recent transactions
//        return List.of(); // Placeholder
//    }
//
//    public List<Category> getAllCategories() {
//        return categoryRepo.findAll();
//    }


    // Create a new transaction
    public void addTransaction(Transaction transaction) {
        Category category = categoryRepo.findById(transaction.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        transaction.setCategory(category);

        Transaction newTransaction = transactionRepo.save(transaction);
        newTransaction.setCategory(transaction.getCategory());
        newTransaction.setDescription(transaction.getDescription());
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setType(transaction.getType());
        newTransaction.setTransactionDate(transaction.getTransactionDate());
        transactionRepo.save(transaction);



    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    // Get a transaction by ID
    public Transaction getTransactionById(long id) {
        return transactionRepo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // Update a transaction
    public Transaction updateTransaction(long id, Transaction updatedTransaction) {
        return transactionRepo.findById(id)
                .map(transaction -> {
                    transaction.setAmount(updatedTransaction.getAmount());
                    transaction.setDescription(updatedTransaction.getDescription());
                    transaction.setTransactionDate(updatedTransaction.getTransactionDate());
                    transaction.setType(updatedTransaction.getType());
                    transaction.setCategory(updatedTransaction.getCategory());
                    return transactionRepo.save(transaction);
                }).orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }

    // Delete a transaction
    public void deleteTransaction(long id) {
        transactionRepo.deleteById(id);
    }

//    public BigDecimal getTotalIncome() {
//        return transactionRepo.findAll().stream()
//                .filter(transaction -> transaction.getType() == TransactionType.INCOME)
//                .map(Transaction::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//
//    public BigDecimal getTotalExpenses() {
//        return transactionRepo.findAll().stream()
//                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
//                .map(Transaction::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

//    public List<Transaction> getRecentTransactions() {
//        // You can adjust this method to fetch recent transactions based on your criteria
//        return transactionRepo.findTop10ByOrderByTransactionDateDesc();
//    }
public List<Transaction> getRecentTransactions() {
    return transactionRepo.findAll(Sort.by(Sort.Direction.DESC, "transactionDate"));
}

//    public List<Transaction> getFilteredTransactions(LocalDate startDate, LocalDate endDate, TransactionType type, Long categoryId) {
//        if (startDate != null && endDate != null) {
//            return transactionRepo.findByTransactionDateBetweenAndTypeAndCategoryId(startDate, endDate, type, categoryId);
//        } else if (startDate != null) {
//            return transactionRepo.findByTransactionDateAfterAndTypeAndCategoryId(startDate, type, categoryId);
//        } else if (endDate != null) {
//            return transactionRepo.findByTransactionDateBeforeAndTypeAndCategoryId(endDate, type, categoryId);
//        } else {
//            return transactionRepo.findByTypeAndCategoryId(type, categoryId);
//        }
//    }

    public List<Transaction> getFilteredTransactions(LocalDate startDate, LocalDate endDate, TransactionType type, Long categoryId) {
        if (startDate != null && endDate != null && type != null && categoryId != null) {
            return transactionRepo.findByTransactionDateBetweenAndTypeAndCategoryId(startDate, endDate, type, categoryId);
        } else if (startDate != null && endDate != null && type != null) {
            return transactionRepo.findByTransactionDateBetweenAndType(startDate, endDate, type);
        } else if (startDate != null && endDate != null) {
            return transactionRepo.findByTransactionDateBetween(startDate, endDate);
        } else if (startDate != null && type != null && categoryId != null) {
            return transactionRepo.findByTransactionDateAfterAndTypeAndCategoryId(startDate, type, categoryId);
        } else if (startDate != null && type != null) {
            return transactionRepo.findByTransactionDateAfterAndType(startDate, type);
        } else if (startDate != null) {
            return transactionRepo.findByTransactionDateAfter(startDate);
        } else if (endDate != null && type != null && categoryId != null) {
            return transactionRepo.findByTransactionDateBeforeAndTypeAndCategoryId(endDate, type, categoryId);
        } else if (endDate != null && type != null) {
            return transactionRepo.findByTransactionDateBeforeAndType(endDate, type);
        } else if (endDate != null) {
            return transactionRepo.findByTransactionDateBefore(endDate);
        } else if (type != null && categoryId != null) {
            return transactionRepo.findByTypeAndCategoryId(type, categoryId);
        } else if (type != null) {
            return transactionRepo.findByType(type);
        } else if (categoryId != null) {
            return transactionRepo.findByCategoryId(categoryId);
        } else {
            return transactionRepo.findAll();
        }
    }


    public void save(Transaction transaction) {
        transactionRepo.save(transaction);
    }
}
