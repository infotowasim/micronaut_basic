package com.wasim.postconstructorandpredistroy;

import jakarta.inject.Singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Singleton
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    @PostConstruct
    public void initialize(){
        System.out.println("This will be called after bean initialization");
        name="Wasim Akram";
    }

    @PreDestroy
    public void destroy(){
        System.out.println("This will be called after before bean destruction");
    }
}
