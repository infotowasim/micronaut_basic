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
class HelloWorldServiceTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void HelloWorldFieldBasedConstructorTest1(){
        var response = client.toBlocking().retrieve("/field");
        assertEquals("Hello From Service!!!",response);
    }


    @Test
    void HelloWorldFieldBasedConstructorTest2(){
        var response = client.toBlocking().exchange("/field", String.class);
        assertEquals(HttpStatus.OK,response.getStatus());
        assertEquals("Hello From Service!!!",response.getBody().get());
    }

}
