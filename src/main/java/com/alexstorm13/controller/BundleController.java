package com.alexstorm13.controller;

import com.alexstorm13.entity.Bundle;
import com.alexstorm13.entity.User;
import com.alexstorm13.repository.BundleRepository;
import org.springframework.web.bind.annotation.*;

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
    Bundle createBundle(@RequestBody Bundle bundle){
        bundleRepository.save(bundle);
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
