package com.wasim;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldConstructorBasedTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void HelloWorldConstructorBasedDI1(){
        var response = client.toBlocking().retrieve("/hello");
        assertEquals("Constructor Based DI!!!",response);
    }


    @Test
    void HelloWorldConstructorBasedDI2(){
        var response = client.toBlocking().exchange("/hello", String.class);
        assertEquals(HttpStatus.OK,response.getStatus());
        assertEquals("Constructor Based DI!!!",response.getBody().get());
    }

}
