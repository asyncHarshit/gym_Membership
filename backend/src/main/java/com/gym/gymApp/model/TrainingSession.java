package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser trainer;

    @ManyToOne
    private GymUser member;

    private String sessionType;
    private LocalDateTime startsAt;
    private int capacity;

    protected TrainingSession() {
    }

    public TrainingSession(GymUser trainer, String sessionType, LocalDateTime startsAt, int capacity) {
        this.trainer = trainer;
        this.sessionType = sessionType;
        this.startsAt = startsAt;
        this.capacity = capacity;
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

    public String getSessionType() {
        return sessionType;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public int getCapacity() {
        return capacity;
    }

    public void book(GymUser member) {
        this.member = member;
    }
}
