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
@RequestMapping("/software")
public class SoftwareController {
    private SoftwareRepository softwareRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final NotificationRepository notificationRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final BundleRepository bundleRepository;

    public SoftwareController(SoftwareRepository softwareRepository, AccessTokenRepository accessTokenRepository, NotificationRepository notificationRepository, SessionRepository sessionRepository, UserRepository userRepository, BundleRepository bundleRepository) {
        this.softwareRepository = softwareRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.notificationRepository = notificationRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.bundleRepository = bundleRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    Iterable<Software> getSoftware() {
        Iterable<Software> software = softwareRepository.findAll();
        return software;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Object getSoftwareById(@PathVariable Long id, @RequestHeader String token) {
        if (accessTokenRepository.findByToken(token) != null) {
            Software software = softwareRepository.findOne(id);
            return software;
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = "application/json")
    Object createSoftware(@RequestBody Software software, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            if (Objects.equals(user.getRole(), "admin")) {
                softwareRepository.save(software);
                return software;
            } else {
                return notificationRepository.findByName("no_admin");
            }
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, consumes = "application/json")
    Object updateSoftware(@RequestBody Software software, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            if (Objects.equals(user.getRole(), "admin")) {
                softwareRepository.save(software);
                return software;
            } else {
                return notificationRepository.findByName("no_admin");
            }
        } else {
            return notificationRepository.findByName("token_mis_match");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    Boolean deleteSoftware(@PathVariable Long id, @RequestHeader String token, @RequestHeader String key) {
        if (accessTokenRepository.findByToken(token) != null) {
            Session session = sessionRepository.findByKey(key);
            User user = userRepository.findBySession(session);
            if (Objects.equals(user.getRole(), "admin")) {
                Software software = softwareRepository.findOne(id);
                Iterable<Bundle> bundles = bundleRepository.findAll();
                for (Bundle bundle : bundles) {
                    if (bundle.getSoftware().contains(software)) {
                        bundle.removeSoftware(software);
                    }
                }
                softwareRepository.delete(id);
                return softwareRepository.findOne(id) == null;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
