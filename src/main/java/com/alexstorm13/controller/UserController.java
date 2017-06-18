package com.alexstorm13.controller;

import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.AccessTokenRepository;
import com.alexstorm13.repository.NotificationRepository;
import com.alexstorm13.repository.SessionRepository;
import com.alexstorm13.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 11/05/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final SessionRepository sessionRepository;
    private final NotificationRepository notificationRepository;

    public UserController(UserRepository userRepository, AccessTokenRepository accessTokenRepository, SessionRepository sessionRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.sessionRepository = sessionRepository;
        this.notificationRepository = notificationRepository;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    Object getUser(@RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            return user;
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/patch", consumes = "application/json")
    Object setUser(@RequestHeader String token, @RequestHeader String key, @RequestBody User user) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User baseUser = userRepository.findBySession(session);
            if (Objects.equals(baseUser.getId(), user.getId())) {
                userRepository.save(user);
            }
            return user;
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }
}
