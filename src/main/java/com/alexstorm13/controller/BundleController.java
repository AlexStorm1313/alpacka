package com.alexstorm13.controller;

import com.alexstorm13.entity.Bundle;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.BundleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@RestController
@RequestMapping("/bundle")
public class BundleController {
    private final BundleRepository bundleRepository;

    public BundleController(BundleRepository bundleRepository) {
        this.bundleRepository = bundleRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    Iterable<Bundle> getBundles() {
        Iterable<Bundle> bundles = bundleRepository.findAll();
        return bundles;
    }
}
