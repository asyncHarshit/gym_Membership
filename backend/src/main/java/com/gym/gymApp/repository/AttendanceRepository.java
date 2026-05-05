package com.gym.membership.repository;

import com.gym.membership.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
/**
 * Repository interface for Attendance collection in MongoDB
 * Provides built-in CRUD operations using MongoRepository
 */
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    List<Attendance> findByUserId(String userId);
}
