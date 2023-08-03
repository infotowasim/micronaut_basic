package com.wasim.controller;

import com.wasim.services.HelloWorldService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/field")
public class HelloWorldController {

    @Inject
    private HelloWorldService service;

    @Get("/")
    public String helloWorld(){
        return service.helloFromService();
    }
}
