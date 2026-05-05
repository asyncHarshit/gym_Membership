package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser member;

    @ManyToOne(optional = false)
    private MembershipPlan plan;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    protected Membership() {
    }

    public Membership(GymUser member, MembershipPlan plan, LocalDate startDate, LocalDate endDate) {
        this.member = member;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = MembershipStatus.ACTIVE;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void renew(MembershipPlan plan, LocalDate startDate) {
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(plan.getDurationDays());
        this.status = MembershipStatus.ACTIVE;
    }

    public void freeze() {
        this.status = MembershipStatus.FROZEN;
    }

    public void activate() {
        this.status = MembershipStatus.ACTIVE;
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
    }
}
