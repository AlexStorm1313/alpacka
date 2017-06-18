package com.alexstorm13.controller;

import com.alexstorm13.entity.Software;
import com.alexstorm13.repository.SoftwareRepository;
import javafx.application.Application;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@RestController
@RequestMapping("/software")
public class SoftwareController {
    private SoftwareRepository softwareRepository;

    public SoftwareController(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    Iterable<Software> getSoftware() {
        Iterable<Software> software = softwareRepository.findAll();
        return software;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Software getSoftwareById(@PathVariable Long id) {
        Software software = softwareRepository.findOne(id);
        return software;
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = "application/json")
    Software createSoftware(@RequestBody Software software) {
        softwareRepository.save(software);
        return software;
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, consumes = "application/json")
    Software updateSoftware(@RequestBody Software software) {
        softwareRepository.save(software);
        return software;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    Boolean deleteSoftware(@PathVariable Long id) {
        softwareRepository.delete(id);
        return softwareRepository.findOne(id) == null;
    }
}
