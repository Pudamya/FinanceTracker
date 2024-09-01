package com.example.financetracker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true,nullable=false)
    @NotEmpty(message = "Username is required")
    @Size(min = 5,max=10, message = "Username should be between 5 to 10")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address!")
    @Column(unique=true,nullable=false)
    private String email;

    @Column(unique=true,nullable=false)
    @NotEmpty(message = "Password is required")
    private String password;

    public User() {
    }

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
