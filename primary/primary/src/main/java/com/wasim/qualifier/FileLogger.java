package com.wasim.qualifier;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Secondary;
import jakarta.inject.Singleton;

@Singleton
@Secondary

public class FileLogger implements Logger{
    @Override
    public void log() {
        System.out.println("Logging into file");
    }
}
