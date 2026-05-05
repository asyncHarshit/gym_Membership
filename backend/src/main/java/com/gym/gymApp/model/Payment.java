package com.example.gym.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser member;

    @ManyToOne(optional = false)
    private MembershipPlan plan;

    private BigDecimal amount;
    private String gatewayReference;
    private Instant paidAt;

    protected Payment() {
    }

    public Payment(GymUser member, MembershipPlan plan, BigDecimal amount, String gatewayReference) {
        this.member = member;
        this.plan = plan;
        this.amount = amount;
        this.gatewayReference = gatewayReference;
        this.paidAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public GymUser getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getGatewayReference() {
        return gatewayReference;
    }

    public Instant getPaidAt() {
        return paidAt;
    }
}
