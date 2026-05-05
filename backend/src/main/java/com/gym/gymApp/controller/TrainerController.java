package com.example.gym.controller;

import com.example.gym.dto.Requests.CreateSessionRequest;
import com.example.gym.dto.Requests.DietPlanRequest;
import com.example.gym.model.BodyMetric;
import com.example.gym.model.DietPlan;
import com.example.gym.model.TrainingSession;
import com.example.gym.model.WorkoutLog;
import com.example.gym.service.GymService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;


// trainer controller

// trainer controller have been added 
@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private final GymService gymService;

    public TrainerController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping("/diet-plans")
    public DietPlan assignDietPlan(@Valid @RequestBody DietPlanRequest request) {
        return gymService.assignDietPlan(request);
    }

    @PostMapping("/sessions")
    public TrainingSession createSession(@Valid @RequestBody CreateSessionRequest request) {
        return gymService.createSession(request);
    }

    @GetMapping("/clients/{memberId}/progress")
    public List<BodyMetric> clientProgress(@PathVariable Long memberId) {
        return gymService.progress(memberId);
    }


    // for getting the client
    @GetMapping("/clients/{memberId}/workouts")
    public List<WorkoutLog> clientWorkouts(@PathVariable Long memberId) {
        return gymService.workouts(memberId);
    }
}
