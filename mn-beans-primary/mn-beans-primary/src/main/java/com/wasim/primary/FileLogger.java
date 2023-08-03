package com.wasim.primary;

import jakarta.inject.Singleton;

@Singleton
public class FileLogger implements Logger {

    @Override
    public void log() {
        System.out.println("Logging the msg into file");
    }
}
