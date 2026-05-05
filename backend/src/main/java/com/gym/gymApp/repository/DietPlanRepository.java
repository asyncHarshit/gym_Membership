package com.gym.membership.repository;

import com.gym.membership.model.DietPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Repository interface for DietPlan collection in MongoDB
 * It extends MongoRepository to provide basic CRUD operations
 */
public interface DietPlanRepository extends MongoRepository<DietPlan, String> {
    /**
     * Find all diet plans by a specific member ID
     * @param memberId - ID of the member
     * @return list of DietPlan objects
     */
    List<DietPlan> findByMemberId(String memberId);
    List<DietPlan> findByTrainerId(String trainerId);
}
