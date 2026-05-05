package com.gym.gymApp.service;

import com.gym.membership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ReminderService {
    private static final Logger logger = Logger.getLogger(ReminderService.class.getName());
    private static final String MEMBER_ROLE = "ROLE_MEMBER";

    private final UserRepository userRepository;

    @Autowired
    public ReminderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Scheduled task that runs daily at midnight.
     * This currently simulates sending reminder notifications to active members.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendMembershipReminders() {
        logger.info("Membership reminder job started.");

        userRepository.findAll().stream()
            .filter(u -> u != null && u.isActive() && u.getRole() != null)
            .filter(u -> MEMBER_ROLE.equals(u.getRole().name()))
            .forEach(u -> logger.info("Reminder sent to: " + u.getEmail()));

        logger.info("Membership reminder job completed.");
    }
}
