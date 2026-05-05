package com.example.gym.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private GymUser member;

    private Instant checkedInAt;
    private String qrCode;
    private Double latitude;
    private Double longitude;

    protected Attendance() {
    }

    public Attendance(GymUser member, String qrCode, Double latitude, Double longitude) {
        this.member = member;
        this.qrCode = qrCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.checkedInAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public GymUser getMember() {
        return member;
    }

    public Instant getCheckedInAt() {
        return checkedInAt;
    }

    public String getQrCode() {
        return qrCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
