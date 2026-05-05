package com.gym.membership.repository;

import com.gym.membership.model.WorkoutLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface WorkoutLogRepository extends MongoRepository<WorkoutLog, String> {
    List<WorkoutLog> findByUserId(String userId);
}
