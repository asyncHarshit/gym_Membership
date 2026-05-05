package com.example.gym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    private LocalDate nextMaintenanceDate;

    protected Equipment() {
    }

    public Equipment(String name, EquipmentStatus status, LocalDate nextMaintenanceDate) {
        this.name = name;
        this.status = status;
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }
}
