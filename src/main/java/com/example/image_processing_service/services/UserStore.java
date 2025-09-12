package com.example.image_processing_service.services;

import com.example.image_processing_service.models.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserStore {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.put(user.getUsername(), user);
        return user;
    }
}
