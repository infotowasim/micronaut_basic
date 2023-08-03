package com.wasim.qualifier;

import jakarta.inject.Singleton;

@Singleton
public class FileLogger implements Logger {


    @Override
    public void log() {
        System.out.println("Logging the msg to file");
    }
}
