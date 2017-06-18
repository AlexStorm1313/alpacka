package com.alexstorm13.controller;

import com.alexstorm13.entity.AccessToken;
import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.AccessTokenRepository;
import com.alexstorm13.repository.NotificationRepository;
import com.alexstorm13.repository.SessionRepository;
import com.alexstorm13.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@CrossOrigin
@RestController
public class SessionController {
    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final NotificationRepository notificationRepository;
    private final SessionRepository sessionRepository;

    public SessionController(UserRepository userRepository, AccessTokenRepository accessTokenRepository, NotificationRepository notificationRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.notificationRepository = notificationRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    Object login(@RequestHeader(value = "token") String token, @RequestBody User user) {
        if (accessTokenRepository.findByToken(token) != null) {
            user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (user != null) {
                return user;
            } else {
                return notificationRepository.findByName("incorrect_credentials");
            }
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    Object register(@RequestHeader(value = "token") String token, @RequestBody User user) {
        if (accessTokenRepository.findByToken(token) != null) {
            if (userRepository.findByEmail(user.getEmail()) == null) {
                Session session = new Session();
                sessionRepository.save(session);
                user.setSession(session);
                userRepository.save(user);
                return user;
            } else {
                return notificationRepository.findByName("email_duplicate");
            }
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }
}
