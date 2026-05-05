package com.gym.membership.repository;

import com.gym.membership.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    List<Attendance> findByUserId(String userId);
}
