package com.wasim;

import com.wasim.qualifier.MyApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class MyApplicationTest {

    @Inject
    private final MyApplication myApplication;

    public MyApplicationTest(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Test
    void shouldLogToFile(){
        myApplication.process();
    }
}
