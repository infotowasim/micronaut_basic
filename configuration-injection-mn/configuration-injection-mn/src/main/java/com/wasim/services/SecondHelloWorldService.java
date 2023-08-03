package com.wasim.services;

import jakarta.inject.Singleton;

@Singleton
public class SecondHelloWorldService implements MyService{

    public String helloFromService(){
        return "Hello From Second Services!!!";
    }

}
