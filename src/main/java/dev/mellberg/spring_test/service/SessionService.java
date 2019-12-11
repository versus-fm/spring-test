package dev.mellberg.spring_test.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mellberg.spring_test.domain.Session;
import dev.mellberg.spring_test.domain.User;
import dev.mellberg.spring_test.repository.SessionRepository;

@Component
public class SessionService {
    @Autowired
    private SessionRepository sessions;

    public Optional<Session> getSession(String sessionId) {
        var session = sessions.getSessionBySessionId(sessionId);
        if(session != null) {
            return Optional.of(session);
        } else {
            return Optional.empty();
        }
    }

    public Session createSession(User user) {
        var session = new Session(user, UUID.randomUUID().toString());
        sessions.save(session);
        return session;
    }

    public List<Session> removeSession(String sessionId) {
        return sessions.deleteBySessionId(sessionId);
    }
}