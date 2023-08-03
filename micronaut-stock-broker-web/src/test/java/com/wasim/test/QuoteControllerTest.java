package com.wasim.test;

import com.wasim.error.CustomError;
import com.wasim.model.Quote;
import com.wasim.model.Symbol;
import com.wasim.store.InMemoryStore;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static io.micronaut.http.HttpRequest.GET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class QuoteControllerTest {
    private static final Logger LOG= LoggerFactory.getLogger(QuoteControllerTest.class);
    @Inject
    EmbeddedApplication<?> application;
    @Inject
    @Client("/")
    RxHttpClient client;
    @Inject
    InMemoryStore store;


    @Test
    void returnsQuotePerSymbol() {
        final Quote  apple=initRandomQuote("AAA");
        store.update(apple);

        final Quote  amazon=initRandomQuote("BBB");
        store.update(amazon);

       final Quote appleResult = client.toBlocking().retrieve(GET("/quotes/AAA"), Quote.class);
       LOG.debug("Result: {}", appleResult);
       assertThat(apple).isEqualToComparingFieldByField(appleResult);


        final Quote amazonResult = client.toBlocking().retrieve(GET("/quotes/AAA"), Quote.class);
        LOG.debug("Result: {}", amazonResult);
        assertThat(amazon).isEqualToComparingFieldByField(amazonResult);
    }

    @Test
   void returnsNotFoundOnUnsupportedSymbol(){
        try {
            client.toBlocking().retrieve(GET("/quotes/UNSUPPORTED"),
            Argument.of(Quote.class),
            Argument.of(CustomError.class)
            );

        }catch (HttpClientResponseException e){
          assertEquals(HttpStatus.NOT_FOUND, e.getResponse().getStatus());
          LOG.debug("Body: {}", e.getResponse().getBody(CustomError.class));
           final Optional<CustomError> customError = e.getResponse().getBody(CustomError.class);
           assertTrue(customError.isPresent());
           assertEquals(404,customError.get().getStatus());
           assertEquals("NOT_FOUND", customError.get().getError());
           assertEquals("quote for symbol not available", customError.get().getMessage());
           assertEquals("/quotes/UNSUPPORTED", customError.get().getPath());
        }
    }


    private Quote initRandomQuote(String symbolValue) {

       return Quote.builder().symbol(new Symbol(symbolValue))
                .bid(randomvalue())
                .ask(randomvalue())
                .lastPrice(randomvalue())
                .volume(randomvalue())
                .build();
    }



    private BigDecimal randomvalue() {
        return  BigDecimal
                .valueOf(ThreadLocalRandom.current().nextDouble(1,100));
    }

}
