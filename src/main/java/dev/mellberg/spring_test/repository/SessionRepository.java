package dev.mellberg.spring_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.mellberg.spring_test.domain.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    public Session getSessionBySessionId(String sessionId);
    public List<Session> deleteBySessionId(String sessionId);
}