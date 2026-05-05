package com.example.gym.controller;

import com.example.gym.dto.Requests.*;
import com.example.gym.model.*;
import com.example.gym.service.GymService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;






// admin controller

// admin controller have been added

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final GymService gymService;

    public MemberController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping("/register")
    public GymUser register(@Valid @RequestBody RegisterUserRequest request) {
        return gymService.register(request);
    }

    @GetMapping("/plans")
    public List<MembershipPlan> plans() {
        return gymService.plans();
    }

    @PostMapping("/purchase")
    public Membership purchase(@Valid @RequestBody PurchasePlanRequest request) {
        return gymService.purchasePlan(request);
    }

    @PostMapping("/check-in")
    public Attendance checkIn(@Valid @RequestBody CheckInRequest request) {
        return gymService.checkIn(request);
    }

    @PostMapping("/workouts")
    public WorkoutLog logWorkout(@Valid @RequestBody WorkoutLogRequest request) {
        return gymService.logWorkout(request);
    }

    @GetMapping("/{memberId}/workouts")
    public List<WorkoutLog> workouts(@PathVariable Long memberId) {
        return gymService.workouts(memberId);
    }

    @PostMapping("/metrics")
    public BodyMetric addMetric(@Valid @RequestBody BodyMetricRequest request) {
        return gymService.addMetric(request);
    }

    @GetMapping("/{memberId}/progress")
    public List<BodyMetric> progress(@PathVariable Long memberId) {
        return gymService.progress(memberId);
    }

    @GetMapping("/{memberId}/diet-plans")
    public List<DietPlan> dietPlans(@PathVariable Long memberId) {
        return gymService.dietPlans(memberId);
    }

    @GetMapping("/sessions")
    public List<TrainingSession> availableSessions() {
        return gymService.availableSessions();
    }

    @PostMapping("/sessions/{sessionId}/book")
    public TrainingSession bookSession(@PathVariable Long sessionId, @Valid @RequestBody BookSessionRequest request) {
        return gymService.bookSession(sessionId, request);
    }
}
