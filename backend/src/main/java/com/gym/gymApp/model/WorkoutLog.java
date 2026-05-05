package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WorkoutLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser member;

    private LocalDate workoutDate;
    private String exercise;
    private int sets;
    private int reps;
    private double weightKg;

    protected WorkoutLog() {
    }

    public WorkoutLog(GymUser member, LocalDate workoutDate, String exercise, int sets, int reps, double weightKg) {
        this.member = member;
        this.workoutDate = workoutDate;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weightKg = weightKg;
    }

    public Long getId() {
        return id;
    }

    public GymUser getMember() {
        return member;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public String getExercise() {
        return exercise;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeightKg() {
        return weightKg;
    }
}
