package com.example.financetracker.controller;

import com.example.financetracker.models.Category;
import com.example.financetracker.models.Transaction;
import com.example.financetracker.models.TransactionType;
import com.example.financetracker.models.User;
import com.example.financetracker.services.CategoryService;
import com.example.financetracker.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transaction-entry";
    }

    @GetMapping("/new")
    public String newTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.AllCategories());
        return "transaction-entry";
    }

//    @PostMapping("/new")
//    public String processNewTransaction(@Valid Transaction transaction, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "transaction-entry";
//        }else
//            transactionService.save(transaction);
//            return "dashboard";
//
//    }

    @PostMapping("/new")
    public String saveTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "transaction-entry";
        }
        transactionService.save(transaction);
        return "dashboard";
    }

    @GetMapping("/history")
    public String showTransactionHistory(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "transaction-history";
    }
//    // Display all transactions
//    @RequestMapping
//    public String getAllTransactions(Model model) {
//
//        model.addAttribute("transactions", transactionService.getAllTransactions());
//        return "transaction-history";
//    }
//
//    // Display form to add a new transaction
//    @RequestMapping("/new")
//    public String showAddTransactionForm(Model model) {
//        model.addAttribute("transaction", new Transaction());
//        model.addAttribute("transactionTypes", TransactionType.values());
//        List<Category> categories = categoryService.getAllCategories();
//        model.addAttribute("categories", categories);
//        return "transactions/new :: transaction-entry";
//    }
//
//    // Handle the submission of a new transaction
////    @PostMapping
////    public String addTransaction(@ModelAttribute("transaction") Transaction transaction) {
////        transactionService.addTransaction(transaction);
////        return "dashboard";
////    }
//    @PostMapping("/new")
//    public String addTransaction(@ModelAttribute("transaction") @Valid Transaction transaction, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            // If there are validation errors, return to the form page
//            model.addAttribute("transactionTypes", TransactionType.values());
//            List<Category> categories = categoryService.getAllCategories(); // Fetch all categories again
//            model.addAttribute("categories", categories);
//            return "transaction-entry";
//        }
//
//        transactionService.addTransaction(transaction);
////        model.addAttribute("totalIncome", transactionService.getTotalIncome());
////        model.addAttribute("totalExpenses", transactionService.getTotalExpenses());
////        model.addAttribute("recentTransactions", transactionService.getRecentTransactions());
//        return "redirect:/user/dashboard"; // Redirect to the dashboard after adding a transaction
//    }
//
//    // Display form to edit an existing transaction
//    @GetMapping("/edit/{id}")
//    public String showEditTransactionForm(@PathVariable long id, Model model) {
//
//        model.addAttribute("transaction", transactionService.getTransactionById(id));
//        return "transaction-entry"; // Thymeleaf template for transaction form
//    }
//
//    // Handle the submission of an edited transaction
//    @PostMapping("/edit/{id}")
//    public String updateTransaction(@PathVariable long id, @ModelAttribute("transaction") @Valid Transaction transaction, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("transactionTypes", TransactionType.values());
//            List<Category> categories = categoryService.getAllCategories(); // Fetch all categories again
//            model.addAttribute("categories", categories);
//            return "transaction-entry"; // Return to the form with validation errors
//        }
//
//        transactionService.updateTransaction(id, transaction);
//        return "redirect:/transactions";
//    }
//
//    // Handle deleting a transaction
//    @GetMapping("/delete/{id}")
//    public String deleteTransaction(@PathVariable long id) {
//        transactionService.deleteTransaction(id);
//        return "redirect:/transactions";
//    }
//    @GetMapping("/history")
//    public String getFilteredTransactions(
//            @RequestParam(required = false) LocalDate startDate,
//            @RequestParam(required = false) LocalDate endDate,
//            @RequestParam(required = false) TransactionType type,
//            @RequestParam(required = false) Long category,
//            Model model) {
//
//        List<Transaction> transactions = transactionService.getFilteredTransactions(startDate, endDate, type, category);
//        List<Category> categories = categoryService.getAllCategories(); // Fetch categories for the filter dropdown
//
//        model.addAttribute("transactions", transactions);
//        model.addAttribute("categories", categories); // Ensure categories are available for filtering
//        model.addAttribute("selectedStartDate", startDate);
//        model.addAttribute("selectedEndDate", endDate);
//        model.addAttribute("selectedType", type);
//        model.addAttribute("selectedCategory", category);
//
//        return "transactions/history :: transaction-history";
//    }
//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        return getString(model, transactionService);
//    }
//
    static String getString(Model model, TransactionService transactionService) {
        BigDecimal totalIncome = transactionService.getTotalIncome();
        BigDecimal totalExpenses = transactionService.getTotalExpenses();
        List<Transaction> recentTransactions = transactionService.getRecentTransactions();

        model.addAttribute("totalIncome", transactionService.getTotalIncome());
        model.addAttribute("totalExpenses", transactionService.getTotalExpenses());
        model.addAttribute("transactions", transactionService.getRecentTransactions());

        return "dashboard"; // Thymeleaf template name
    }


//    @Autowired
//    private TransactionService transactionService;
//
//    @GetMapping("/transactions")
//    public String showTransactionForm(Model model) {
//        model.addAttribute("transaction", new Transaction());
//        model.addAttribute("categories", transactionService.getAllCategories());
//        return "transaction-entry";
//    }
//    @PostMapping("/transactions")
//    public String saveTransaction(Transaction transaction) {
//        transactionService.saveTransaction(transaction);
//        return "redirect:/dashboard";
//    }











}
