package com.wasim;

import com.wasim.controller.WatchListController;
import com.wasim.model.WatchList;
import com.wasim.services.InMemoryAccountStore;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class WatchListControllerTest {

    private static final UUID TEST_ACCOUNT_ID= WatchListController.ACCOUNT_ID;

    @Inject
    EmbeddedApplication application;

    @Inject
    @Client("/account/watchlist")
    HttpClient client;

    @Inject
    InMemoryAccountStore store;


    @Test
    void returnsEmptyWatchListForAccount() {
       final WatchList result = client.toBlocking().retrieve("/", WatchList.class);
       assertTrue(result.getSymbols().isEmpty());
       assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
    }

}
