package com.pj2z.pj2zbe.scheduler;

import com.pj2z.pj2zbe.user.entity.User;
import com.pj2z.pj2zbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseTicketJob {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 1 * *")
    public void baseTicketUpdate(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setBaseTicket(5));
        userRepository.saveAll(users);
    }
}
