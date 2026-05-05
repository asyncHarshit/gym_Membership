package com.gym.membership.service;

import com.gym.membership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ReminderService {
    
    private static final Logger logger = Logger.getLogger(ReminderService.class.getName());

    @Autowired
    private UserRepository userRepository;

    // Run every day at midnight (simulation)
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendMembershipReminders() {
        logger.info("Checking for expiring memberships...");
        // Logic to find users with planId expiring soon and "send" notifications
        userRepository.findAll().stream()
            .filter(u -> u.isActive() && u.getRole().name().equals("ROLE_MEMBER"))
            .forEach(u -> logger.info("Reminder sent to: " + u.getEmail()));
    }
}
