package com.wasim;

import com.wasim.qualifier.MyApplication;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class MYApplicationTest {

    @Inject
    EmbeddedApplication<?> application;
    @Inject
    private MyApplication myApplication;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void shouldLogToFile(){
        myApplication.process();
    }

}
