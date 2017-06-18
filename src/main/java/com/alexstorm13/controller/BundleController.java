package com.alexstorm13.controller;

import com.alexstorm13.entity.Bundle;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.BundleRepository;
import com.alexstorm13.repository.SessionRepository;
import com.alexstorm13.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

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

    public BundleController(BundleRepository bundleRepository, UserRepository userRepository, SessionRepository sessionRepository) {
        this.bundleRepository = bundleRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    Iterable<Bundle> getBundles() {
        Iterable<Bundle> bundles = bundleRepository.findAll();
        return bundles;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Bundle getBundleById(@PathVariable Long id){
        Bundle bundle = bundleRepository.findOne(id);
        return bundle;
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = "application/json")
    Bundle createBundle(@RequestBody Bundle bundle, @RequestHeader String token, @RequestHeader String key){
        User user = userRepository.findBySession(sessionRepository.findByKey(key));
        bundle.addOwner(user);
        user.addBundle(bundle);
        bundleRepository.save(bundle);
        userRepository.save(user);
        return bundle;
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, consumes = "application/json")
    Bundle updateBundle(@RequestBody Bundle bundle){
        bundleRepository.save(bundle);
        return bundle;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    Boolean deleteBundle(@PathVariable Long id){
        bundleRepository.delete(id);
        return bundleRepository.findOne(id) == null;
    }
}
