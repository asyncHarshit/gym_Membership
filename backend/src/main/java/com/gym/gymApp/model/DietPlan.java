package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser trainer;

    @ManyToOne(optional = false)
    private GymUser member;

    private String title;
    @Column(length = 2000)
    private String meals;
    private LocalDate assignedDate;

    protected DietPlan() {
    }

    public DietPlan(GymUser trainer, GymUser member, String title, String meals) {
        this.trainer = trainer;
        this.member = member;
        this.title = title;
        this.meals = meals;
        this.assignedDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public GymUser getTrainer() {
        return trainer;
    }

    public GymUser getMember() {
        return member;
    }

    public String getTitle() {
        return title;
    }

    public String getMeals() {
        return meals;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }
}
