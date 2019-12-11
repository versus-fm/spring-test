package dev.mellberg.spring_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.mellberg.spring_test.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);
}