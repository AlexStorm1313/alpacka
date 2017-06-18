package com.alexstorm13.controller;

import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.AccessTokenRepository;
import com.alexstorm13.repository.SessionRepository;
import com.alexstorm13.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

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

    public UserController(UserRepository userRepository, AccessTokenRepository accessTokenRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    User getUser(@RequestHeader(value = "token") String token, @RequestHeader(value = "key") String key) {
        Session session = sessionRepository.findByKey(key);
        User user = userRepository.findBySession(session);
        return user;
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/patch", consumes = "application/json")
    User setUser(@RequestHeader(value = "token") String token, @RequestBody User user) {
        userRepository.save(user);
        return user;
    }

}
