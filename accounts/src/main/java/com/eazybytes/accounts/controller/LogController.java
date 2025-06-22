package com.eazybytes.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log")
    public String log(){
        logger.info("log Info");
        //Default logging level is INFO, Hence console logs are missing this line
        logger.debug("log Debug");
        logger.warn("log Warn");
        logger.error("log Error");
        return "Please check your log file";
    }
}
