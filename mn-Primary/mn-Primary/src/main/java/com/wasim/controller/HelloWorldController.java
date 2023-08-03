package com.wasim.controller;

import com.wasim.services.MyService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    @Inject
    private MyService service;

    public HelloWorldController(MyService service) {
        this.service = service;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String HelloWorld(){
        LOG.debug("Called the Hello World API");
        return service.helloFromService();
    }
}
