package com.example.gym.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int durationDays;
    private BigDecimal price;

    protected MembershipPlan() {
    }

    public MembershipPlan(String name, int durationDays, BigDecimal price) {
        this.name = name;
        this.durationDays = durationDays;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
