package com.alexstorm13.controller;

import com.alexstorm13.entity.AccessToken;
import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.AccessTokenRepository;
import com.alexstorm13.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 11/05/2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;

    public UserController(UserRepository userRepository, AccessTokenRepository accessTokenRepository) {
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getUser", consumes = "application/json")
    User getUser(@RequestHeader(value = "token") String token, @RequestBody Session session) {
        User user = userRepository.findBySession(session);
        return user;
    }
}
