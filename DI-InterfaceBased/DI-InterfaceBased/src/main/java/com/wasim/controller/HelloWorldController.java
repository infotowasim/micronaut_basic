package com.wasim.controller;

import com.wasim.services.MyService2;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/hello")
public class HelloWorldController {

    @Inject
    private MyService2 service2;

    public HelloWorldController(MyService2 service2) {
        this.service2 = service2;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld(){
        return service2.helloFromService();
    }
}
