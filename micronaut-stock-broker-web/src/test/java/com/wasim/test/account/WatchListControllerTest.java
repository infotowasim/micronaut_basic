package com.wasim.test.account;

import com.wasim.controller.account.WatchListController;
import com.wasim.controller.model.WatchList;
import com.wasim.controller.store.InMemoryAccountStore;
import com.wasim.error.CustomError;
import com.wasim.model.Quote;
import com.wasim.model.Symbol;
import com.wasim.store.InMemoryStore;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpResponse;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class WatchListControllerTest {
    private static final Logger LOG= LoggerFactory.getLogger(WatchListControllerTest.class);
    private static final UUID TEST_ACCOUNT_ID= WatchListController.ACCOUNT_ID;

    @Inject
    EmbeddedApplication<?> application;
    @Inject
    @Client("/account/watchlist")
    RxHttpClient client;
    @Inject
    InMemoryAccountStore store;


   @Test
    void returnEmptyWatchListForAccount(){
     final WatchList result = client.toBlocking().retrieve("/", WatchList.class);
     assertTrue(result.getSymbols().isEmpty());
     assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
   }


   @Test
    void returnWatchListForAccount(){
      final List<Symbol> symbols = Stream.of("AAA", "BBB", "CCC")
               .map(Symbol::new)
               .collect(Collectors.toList());
       WatchList watchList=new WatchList(symbols);
       store.updateWatchList(TEST_ACCOUNT_ID, watchList);


       final WatchList result = client.toBlocking().retrieve("/", WatchList.class);
       assertEquals(3, result.getSymbols().size());
       assertEquals(3, store.getWatchList(TEST_ACCOUNT_ID).getSymbols().size());
   }


   @Test
    void canUpdateWatchListForAccount(){

       final List<Symbol> symbols = Stream.of("AAA", "BBB", "CCC")
               .map(Symbol::new)
               .collect(Collectors.toList());
       WatchList watchList=new WatchList(symbols);

     final HttpResponse<Object> added = client.toBlocking().exchange(PUT("/", watchList));
     assertEquals(HttpStatus.OK, added.getStatus());
     assertEquals(watchList, store.getWatchList(TEST_ACCOUNT_ID));

   }


   @Test
    void canDeleteWatchListForAccount(){
       final List<Symbol> symbols = Stream.of("AAA", "BBB", "CCC")
               .map(Symbol::new)
               .collect(Collectors.toList());
       WatchList watchList=new WatchList(symbols);
       store.updateWatchList(TEST_ACCOUNT_ID, watchList);
       assertFalse(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());

     final HttpResponse<Object> deleted = client.toBlocking().exchange(DELETE("/" + TEST_ACCOUNT_ID));
     assertEquals(HttpStatus.OK, deleted.getStatus());
     assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
   }

}
