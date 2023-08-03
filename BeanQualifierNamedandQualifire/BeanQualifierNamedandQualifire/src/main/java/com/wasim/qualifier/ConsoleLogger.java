package com.wasim.qualifier;

import jakarta.inject.Singleton;

@Singleton
public class ConsoleLogger implements Logger{
    @Override
    public void log() {
     System.out.println("Logging to Console");
    }
}
