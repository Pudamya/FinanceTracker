package com.example.financetracker.services;

import com.example.financetracker.models.Category;
import com.example.financetracker.models.TransactionType;
import com.example.financetracker.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> AllCategories() {
        // Example static data; replace with actual data fetching logic
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Groceries", TransactionType.EXPENSE));
        categories.add(new Category(2L, "Salary", TransactionType.INCOME));
        categories.add(new Category(3L, "Entertainment", TransactionType.EXPENSE));
        categories.add(new Category(4L, "Fixed Deposit Interest", TransactionType.INCOME));
        categories.add(new Category(5L, "Transportation", TransactionType.EXPENSE));
        categories.add(new Category(6L, "Rental Income", TransactionType.INCOME));
        categories.add(new Category(7L, "Healthcare", TransactionType.EXPENSE));

        return categories;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    public void save(Category category) {
        categoryRepo.save(category);
    }
}
