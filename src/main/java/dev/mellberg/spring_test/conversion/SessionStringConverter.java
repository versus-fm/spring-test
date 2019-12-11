package dev.mellberg.spring_test.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import dev.mellberg.spring_test.domain.Session;
import dev.mellberg.spring_test.dto.SessionDetails;

@Component
public class SessionStringConverter implements Converter<Session, SessionDetails> {
    @Override
    public SessionDetails convert(Session session) {
        var details = new SessionDetails();
        details.setSession(session.getSessionId());
        return details;
    }
}