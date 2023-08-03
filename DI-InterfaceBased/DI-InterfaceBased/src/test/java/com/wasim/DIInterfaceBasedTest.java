package com.wasim;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class DIInterfaceBasedTest {


    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void DIInterfaceBasedTest1(){
        var response = client.toBlocking().retrieve("/hello");
        assertEquals("Interface Based DI!!!",response);
    }

    @Test
    void DIInterfaceBasedTest2(){
        var response = client.toBlocking().exchange("/hello", String.class);
        assertEquals(HttpStatus.OK,response.getStatus());
        assertEquals("Interface Based DI!!!",response.getBody().get());
    }

}
