package com.wasim.controller;

import com.wasim.model.WatchList;
import com.wasim.services.InMemoryAccountStore;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.UUID;

@Controller("/account/watchlist")
public class WatchListController {

    private final InMemoryAccountStore store;
    public static final UUID ACCOUNT_ID = UUID.randomUUID();

    public WatchListController(InMemoryAccountStore store) {
        this.store = store;
    }


    @Get(produces = MediaType.APPLICATION_JSON)
    public WatchList get(){
        return store.getWatchList(ACCOUNT_ID);
    }


    @Put(produces = MediaType.APPLICATION_JSON,
         consumes = MediaType.APPLICATION_JSON)

    public WatchList update(@Body WatchList watchList){
        return store.updateWatchList(ACCOUNT_ID,watchList);
    }


    @Delete(
            value = "/{accountId}",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)

    public void deleteWatchList(@PathVariable final UUID ACCOUNT_ID) {
        store.deleteWatchList(ACCOUNT_ID);

    }
}
