package com.gym.membership.repository;

import com.gym.membership.model.WorkoutLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface WorkoutLogRepository extends MongoRepository<WorkoutLog, String> {
    /**
     * Retrieve all workout logs for a specific user
     * @param userId - ID of the user
     * @return list of WorkoutLog records
     */
    List<WorkoutLog> findByUserId(String userId);
}
