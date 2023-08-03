package com.wasim;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class HelloController {

    @Get("/hi")
    public String getHello(){
        return "Hello Wasim";
    }
}
