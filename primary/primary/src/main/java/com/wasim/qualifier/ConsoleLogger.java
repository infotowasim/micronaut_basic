package com.wasim.qualifier;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Singleton

public class ConsoleLogger implements Logger{
    @Override
    public void log() {
        System.out.println("Logging to the Console");
    }
}
