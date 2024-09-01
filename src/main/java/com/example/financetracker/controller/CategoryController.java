package com.example.financetracker.controller;

import com.example.financetracker.models.Category;
import com.example.financetracker.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/manage")
    public String viewCategories(Model model) {
        List<Category> categories = categoryService.AllCategories();  // Fetch categories
        model.addAttribute("categories", categories);
        return "category-management";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category-management";
    }

//    @PostMapping("/add")
//    public String addCategory(@ModelAttribute Category category) {
//        categoryService.saveCategory(category);
//        return "redirect:/categories";
//    }
    @PostMapping("/add")
    public String addCategory(@RequestParam String name, @RequestParam String type) {
        Category category = new Category();
        category.setName(name);
        category.setType(type.equalsIgnoreCase("INCOME") ? "INCOME" : "EXPENSE");
        categoryService.saveCategory(category);
        return "redirect:/categories/manage";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//        return "redirect:/categories";
//    }
    @PostMapping("/delete")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/manage";
    }

    @PostMapping
    public String saveOrUpdateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "manage-categories";
        }

        categoryService.save(category);
        return "redirect:/categories/manage";
    }



}
