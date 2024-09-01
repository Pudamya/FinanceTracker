package com.example.financetracker.controller;

import com.example.financetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalIncome", transactionService.getTotalIncome());
        model.addAttribute("totalExpenses", transactionService.getTotalExpenses());
        model.addAttribute("recentTransactions", transactionService.getAllTransactions());
        return "dashboard";
    }
}
