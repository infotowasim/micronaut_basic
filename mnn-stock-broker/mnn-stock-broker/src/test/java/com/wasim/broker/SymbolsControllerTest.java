package com.wasim.broker;

import com.wasim.Symbol;
import com.wasim.data.InMemoryStore;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class SymbolsControllerTest {

    private static final Logger LOG= LoggerFactory.getLogger(SymbolsControllerTest.class);

    @Inject
    @Client("/symbols")
    HttpClient client;

    @Inject
    InMemoryStore inMemoryStore;

    @BeforeEach
    void setup(){
        inMemoryStore.initializeWith(10);

    }

    @Test
    void symbolsEndPointReturnsListOfSymbol() {
        var response=client.toBlocking().exchange("/", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(10, response.getBody().get().size());

    }


    @Test
    void symbolsEndPointReturnsCorrectSymbol() {
        var testSymbol=new Symbol("TEST");
        inMemoryStore.getSymbols().put(testSymbol.value(), testSymbol);
        var response=client.toBlocking().exchange("/"+testSymbol.value(), Symbol.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(testSymbol, response.getBody().get());

    }


    @Test
    void symbolsEndPointReturnsListOfSymbolTakingQueryParametersIntoAccount() {
        var max10=client.toBlocking().exchange("/filter?max=10", JsonNode.class);
        assertEquals(HttpStatus.OK, max10.getStatus());
        LOG.debug("Max: 10: {}", max10.getBody().get()); //toPrettyString()
        assertEquals(10, max10.getBody().get().size());


        var offset7=client.toBlocking().exchange("/filter?offset=7", JsonNode.class);
        assertEquals(HttpStatus.OK, offset7.getStatus());
        LOG.debug("Offset: 7: {}", offset7.getBody().get()); //toPrettyString()
        assertEquals(3, offset7.getBody().get().size());



        var max2Offset7=client.toBlocking().exchange("/filter?max=2&offset=7", JsonNode.class);
        assertEquals(HttpStatus.OK, max2Offset7.getStatus());
        LOG.debug("Max: 2, Offset: 7: {}", max2Offset7.getBody().get()); //toPrettyString()
        assertEquals(2, max2Offset7.getBody().get().size());


    }

}
