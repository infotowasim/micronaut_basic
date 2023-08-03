package com.wasim.controller;

import io.micronaut.http.annotation.Get;

@io.micronaut.http.annotation.Controller
public class Controller {

    @Get("/hello")
    public String getHelloWorld(){
        return "Hello World!!";
    }

}
