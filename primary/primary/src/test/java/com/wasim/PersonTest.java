package com.wasim;

import com.wasim.postconstructorandpredistroy.Person;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class PersonTest {

    @Inject
    private Person person;

    @Test
    void shouldInitializePerson(){
        assertEquals("Wasim Akram",person.getName());
    }
}
