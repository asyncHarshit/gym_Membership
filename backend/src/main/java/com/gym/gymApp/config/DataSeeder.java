package com.example.gym.config;

import com.example.gym.model.*;
import com.example.gym.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


// Data seeders 

// changes have been done according to you 


@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedData(GymUserRepository users, MembershipPlanRepository plans,
                               EquipmentRepository equipment, TrainingSessionRepository sessions,
                               BodyMetricRepository metrics, WorkoutLogRepository workouts) {
        return args -> {
            MembershipPlan monthly = plans.save(new MembershipPlan("Monthly Strength", 30, new BigDecimal("1999")));
            plans.save(new MembershipPlan("Quarterly Transformation", 90, new BigDecimal("4999")));
            plans.save(new MembershipPlan("Annual Elite", 365, new BigDecimal("14999")));

            GymUser admin = users.save(new GymUser("Aarav Manager", "admin@gym.local", Role.ADMIN, "9000000001"));
            GymUser trainer = users.save(new GymUser("Meera Trainer", "trainer@gym.local", Role.TRAINER, "9000000002"));
            GymUser member = users.save(new GymUser("Riya Member", "member@gym.local", Role.MEMBER, "9000000003"));

            equipment.save(new Equipment("Treadmill 01", EquipmentStatus.AVAILABLE, LocalDate.now().plusDays(20)));
            equipment.save(new Equipment("Leg Press", EquipmentStatus.NEEDS_REPAIR, LocalDate.now().plusDays(2)));
            sessions.save(new TrainingSession(trainer, "Yoga", LocalDateTime.now().plusDays(1).withHour(7).withMinute(0), 12));
            sessions.save(new TrainingSession(trainer, "Personal Training", LocalDateTime.now().plusDays(1).withHour(18).withMinute(0), 1));

            metrics.save(new BodyMetric(member, LocalDate.now().minusWeeks(3), 78.5, 24.0, 31.0));
            metrics.save(new BodyMetric(member, LocalDate.now().minusWeeks(2), 77.2, 23.1, 31.4));
            metrics.save(new BodyMetric(member, LocalDate.now().minusWeeks(1), 76.4, 22.5, 31.9));
            workouts.save(new WorkoutLog(member, LocalDate.now().minusDays(2), "Bench Press", 4, 8, 45));

            System.out.printf("Seeded demo users: admin=%d, trainer=%d, member=%d; sample plan=%d%n",
                    admin.getId(), trainer.getId(), member.getId(), monthly.getId());
        };
    }
}
