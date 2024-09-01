package com.example.financetracker.controller;

import com.example.financetracker.models.Transaction;
import com.example.financetracker.models.User;
import com.example.financetracker.services.TransactionService;
import com.example.financetracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;

import static com.example.financetracker.controller.TransactionController.getString;
import static javax.swing.UIManager.getString;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
//    public String registerUser(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            return "register";
//        }else {
//            System.out.println("User Created Id : "+userService.addUser(user));
//            return "dashboard";
//
//
//        }
//    }
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", "Register again there's an error.");
            return "register";
        }

        if (userService.isEmailAlreadyInUse(user.getEmail())) {
            model.addAttribute("emailError", "Email is already in use.");
            return "register";
        }

        userService.save(user);
        return "redirect:/user/login";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }



    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("transactions", transactionService.getRecentTransactions());
        model.addAttribute("totalIncome", transactionService.getTotalIncome());
        model.addAttribute("totalExpenses", transactionService.getTotalExpenses());
        return "dashboard";
    }




//    public String showLoginPage(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
//        User user = userService.findByEmail(email);
//
//        if (user != null && userService.checkPassword(user, password)) {
//            model.addAttribute("user", user);
//            return "redirect:/dashboard"; // Redirect to the dashboard after successful login
//        } else {
//            model.addAttribute("loginError", "Invalid email or password");
//            return "login"; // Return to the login page with an error message
//        }
//    }
//    @PostMapping("/login")
//    public String showLoginPage(@RequestParam(value = "email", required = false) String email,
//                                @RequestParam(value = "password", required = false) String password,
//                                Model model) {
//        if (email == null || password == null) {
//            model.addAttribute("loginError", "Email and password are required.");
//            return "login"; // Return to the login page with an error message
//        }
//        User user = userService.findByEmail(email);
//
//        if (user != null && userService.checkPassword(user, password)) {
//            model.addAttribute("user", user);
//            return "redirect:/dashboard"; // Redirect to the dashboard after successful login
//        } else {
//            model.addAttribute("loginError", "Invalid email or password");
//            return "login"; // Return to the login page with an error message
//        }
//    }

    // Show the login page (GET request)
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should return the login view
    }

    // Handle login form submission (POST request)
    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        User user = userService.findByEmail(email);

        if (user != null && userService.checkPassword(user, password)) {
            model.addAttribute("user", user);
            return "dashboard"; // Redirect to the dashboard after successful login
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login"; // Return to the login page with an error message
        }
    }
}
