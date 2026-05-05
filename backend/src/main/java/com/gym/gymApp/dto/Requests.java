package com.example.gym.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Requests {
    private Requests() {
    }

    public record RegisterUserRequest(@NotBlank String name, @Email String email, @NotBlank String role, String phone) {
    }

    public record PurchasePlanRequest(@NotNull Long memberId, @NotNull Long planId, @NotBlank String gatewayReference) {
    }

    public record CheckInRequest(@NotNull Long memberId, @NotBlank String qrCode, Double latitude, Double longitude) {
    }

    public record WorkoutLogRequest(@NotNull Long memberId, @NotBlank String exercise, @Min(1) int sets,
                                    @Min(1) int reps, @Positive double weightKg, LocalDate workoutDate) {
    }

    public record BodyMetricRequest(@NotNull Long memberId, @Positive double bodyWeightKg,
                                    @PositiveOrZero double bodyFatPercent, @PositiveOrZero double muscleMassKg,
                                    LocalDate metricDate) {
    }

    public record DietPlanRequest(@NotNull Long trainerId, @NotNull Long memberId, @NotBlank String title,
                                  @NotBlank String meals) {
    }

    public record CreateSessionRequest(@NotNull Long trainerId, @NotBlank String sessionType,
                                       @NotNull LocalDateTime startsAt, @Min(1) int capacity) {
    }

    public record BookSessionRequest(@NotNull Long memberId) {
    }
}
