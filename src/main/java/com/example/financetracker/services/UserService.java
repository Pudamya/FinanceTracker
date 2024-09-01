package com.example.financetracker.services;

import com.example.financetracker.models.User;
import com.example.financetracker.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public long addUser(User user) {
        return userRepo.save(user).getId();
    }

    public User findByEmail(String email) {

        return userRepo.findByEmail(email);
    }

    public boolean checkPassword(User user, String password) {

        return user.getPassword().equals(password);
    }
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && password.equals(user.getPassword()) ) {
            return user;
        }
        return null;
    }

    public boolean isEmailAlreadyInUse(String email) {
        return userRepo.findByEmail(email) != null;
    }


    public void save(User user) {
        userRepo.save(user);
    }
}
