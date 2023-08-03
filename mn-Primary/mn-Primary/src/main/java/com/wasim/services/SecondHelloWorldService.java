package com.wasim.services;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;
@Primary
@Singleton
public class SecondHelloWorldService implements MyService {

    public String helloFromService(){
        return "Hello From Second Service!!!";
    }

}
