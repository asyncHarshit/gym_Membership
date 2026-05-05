package com.example.gym.controller;

import com.example.gym.dto.DashboardStats;
import com.example.gym.dto.Requests.PurchasePlanRequest;
import com.example.gym.model.Equipment;
import com.example.gym.model.GymUser;
import com.example.gym.model.Membership;
import com.example.gym.service.GymService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;


// admin controller

// we have added controller in this 
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final GymService gymService;

    public AdminController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/dashboard")
    public DashboardStats dashboard() {
        return gymService.dashboardStats();
    }

    @GetMapping("/members")
    public List<GymUser> members() {
        return gymService.members();
    }

    // adding new member
    @PostMapping("/members/renew")
    public Membership renew(@Valid @RequestBody PurchasePlanRequest request) {
        return gymService.purchasePlan(request);
    }

    @GetMapping("/equipment")
    public List<Equipment> equipment() {
        return gymService.equipment();
    }
}
