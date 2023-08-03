package com.wasim;

import com.wasim.services.HelloWorldService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/hello")
public class HelloWorldController {

    @Inject
    private HelloWorldService service;

    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld(){
        return service.helloWorldService();
    }
}
