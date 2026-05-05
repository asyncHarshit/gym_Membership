package com.gym.membership.repository;

import com.gym.membership.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByMemberId(String memberId);
    List<Booking> findByTrainerId(String trainerId);
}
