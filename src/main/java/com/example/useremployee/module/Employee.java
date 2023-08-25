package com.example.useremployee.module;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime born;
    private Gender gender;
    private boolean vegetarian;

    @OneToOne
    @JoinColumn(name = "useridfk", referencedColumnName = "userId", nullable = false)
    private User user;

    public Employee(){

    }

    public Employee(String name, LocalDateTime born, Gender gender, boolean vegetarian) {
        this.name = name;
        this.born = born;
        this.gender = gender;
        this.vegetarian = vegetarian;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(LocalDateTime born) {
        this.born = born;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBorn() {
        return born;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
