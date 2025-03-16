package com.pj2z.pj2zbe.scheduler;

import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "BaseTicketJob")
@Component
@RequiredArgsConstructor
public class BaseTicketJob {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 1 * *")
    @Transactional
    public void baseTicketUpdate(){
        try{
            List<User> users = userRepository.findAll();
            users.forEach(user -> user.setBaseTicket(5));
            userRepository.saveAll(users);

            log.info("Base ticket updated successfully: {}", users.size());
        } catch (Exception e) {
            log.error("Base ticket update failed: {}", e.getMessage());
        }
    }
}
