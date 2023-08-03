package com.wasim.services;

import jakarta.inject.Singleton;

@Singleton
public class HelloWorldService {
    public String helloWorldService() {
        return "Constructor Based DI!!!";
    }
}
