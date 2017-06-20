package com.alexstorm13.controller;

import com.alexstorm13.entity.Bundle;
import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.Software;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/bundle")
public class BundleController {
    private final BundleRepository bundleRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final NotificationRepository notificationRepository;

    public BundleController(BundleRepository bundleRepository, UserRepository userRepository, SessionRepository sessionRepository, AccessTokenRepository accessTokenRepository, NotificationRepository notificationRepository) {
        this.bundleRepository = bundleRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.notificationRepository = notificationRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    Iterable<Bundle> getBundles() {
        Iterable<Bundle> bundles = bundleRepository.findAll();
        return bundles;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Object getBundleById(@PathVariable Long id, @RequestHeader String token) {
        if (accessTokenRepository.findByToken(token) != null) {
            Bundle bundle = bundleRepository.findOne(id);
            return bundle;
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = "application/json")
    Object createBundle(@RequestBody Bundle bundle, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            User user = userRepository.findBySession(sessionRepository.findByKey(key));
            bundle.addOwner(user);
            user.addBundle(bundle);
            bundleRepository.save(bundle);
            userRepository.save(user);
            return bundle;
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, consumes = "application/json")
    Object updateBundle(@RequestBody Bundle bundle, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            if (bundle.getOwners().contains(user.getId())) {
                bundleRepository.save(bundle);
                return bundle;
            } else {
                return notificationRepository.findByName("no_admin");
            }
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    Boolean deleteBundle(@PathVariable Long id, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            Bundle bundle = bundleRepository.findOne(id);
            if (bundle.getOwners().contains(user.getId())) {
                List<Software> softwareList = new ArrayList<>();
                bundle.setSoftware(softwareList);
                Iterable<User> users = userRepository.findAll();
                for (User uzer : users) {
                    if (uzer.getBundles().contains(bundle)) {
                        uzer.removeBundle(bundle);
                    }
                }
                bundleRepository.delete(id);
                return bundleRepository.findOne(id) == null;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

