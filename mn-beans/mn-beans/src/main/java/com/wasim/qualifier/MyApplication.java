package com.wasim.qualifier;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class MyApplication {

    private final Logger logger;

    public MyApplication(@Console Logger logger) {
        this.logger = logger;
    }

    public void process(){
        System.out.println("Processing");
        logger.log();
    }
}
