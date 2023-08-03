package com.wasim.services;

import jakarta.inject.Singleton;

@Singleton
public class MyService1 implements MyService2{

    @Override
    public String helloFromService(){
        return "Interface Based DI!!!";
    }


}
