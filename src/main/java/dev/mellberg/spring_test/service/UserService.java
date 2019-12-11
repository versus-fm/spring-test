package dev.mellberg.spring_test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dev.mellberg.spring_test.domain.User;
import dev.mellberg.spring_test.repository.UserRepository;

@Component
public class UserService {
    @Autowired
    private UserRepository users;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public Optional<User> validateUser(String username, String password) {
        var user = users.getByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void createTestUser(String username, String password) {
        var user = new User(username, passwordEncoder.encode(password));
        users.save(user);
    }
}