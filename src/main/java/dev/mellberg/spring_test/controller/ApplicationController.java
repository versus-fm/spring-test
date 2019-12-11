package dev.mellberg.spring_test.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.mellberg.spring_test.dto.LoginDetails;
import dev.mellberg.spring_test.dto.SessionDetails;
import dev.mellberg.spring_test.service.SessionService;
import dev.mellberg.spring_test.service.UserService;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConversionService conversion;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginDetails details) {
        var user = userService.validateUser(details.getUsername(), details.getPassword());
        if (user.isPresent()) {
            var session = sessionService.createSession(user.get());
            return ResponseEntity.ok(conversion.convert(session, SessionDetails.class));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/logout")
    public ResponseEntity<Object> logout(@RequestBody SessionDetails sessionId) {
        var session = sessionService.getSession(sessionId.getSession());
        if (session.isPresent()) {
            sessionService.removeSession(sessionId.getSession());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/is-authenticated")
    public ResponseEntity<Object> isAuthenticated(@RequestBody SessionDetails sessionId) {
        var session = sessionService.getSession(sessionId.getSession());
        if (session.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(conversion.convert(session.get(), SessionDetails.class));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}