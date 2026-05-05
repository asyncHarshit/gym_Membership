package com.example.gym.dto;

import java.math.BigDecimal;

public record DashboardStats(long activeMembers, long todayCheckIns, long expiringThisWeek, BigDecimal todayRevenue) {
}
