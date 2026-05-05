package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class BodyMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser member;

    private LocalDate metricDate;
    private double bodyWeightKg;
    private double bodyFatPercent;
    private double muscleMassKg;

    protected BodyMetric() {
    }

    public BodyMetric(GymUser member, LocalDate metricDate, double bodyWeightKg, double bodyFatPercent, double muscleMassKg) {
        this.member = member;
        this.metricDate = metricDate;
        this.bodyWeightKg = bodyWeightKg;
        this.bodyFatPercent = bodyFatPercent;
        this.muscleMassKg = muscleMassKg;
    }

    public Long getId() {
        return id;
    }

    public GymUser getMember() {
        return member;
    }

    public LocalDate getMetricDate() {
        return metricDate;
    }

    public double getBodyWeightKg() {
        return bodyWeightKg;
    }

    public double getBodyFatPercent() {
        return bodyFatPercent;
    }

    public double getMuscleMassKg() {
        return muscleMassKg;
    }
}
