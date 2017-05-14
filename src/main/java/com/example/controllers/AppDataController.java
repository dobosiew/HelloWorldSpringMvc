package com.example.controllers;

import com.example.security.ApplicationUser;
import com.example.security.AuthUser;
import com.example.domain.appdata.AppData;
import com.example.domain.appdata.AppDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/data")
public class AppDataController {

    final Logger logger = LoggerFactory.getLogger(AppDataController.class);

    AppDataRepository appDataRepository;

    @Autowired
    public AppDataController(AppDataRepository appDataRepository) {
        this.appDataRepository = appDataRepository;
    }

    @RequestMapping(path = "/{dataId}", method = RequestMethod.GET)
    public Callable<AppData> getDataById(@PathVariable Long dataId,
                                         @ApplicationUser AuthUser authUser) {
        logger.info("AuthUser: " + authUser);
        return () -> appDataRepository.getAppDataById(dataId);
    }
}
