package com.wasim.watchlist.controller;

import com.wasim.watchlist.model.WatchList;
import com.wasim.watchlist.service.InMemoryAccountStore;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.UUID;
@Controller("/account/watchlist")
public class WatchListController {

    public static final UUID ACCOUNT_ID=UUID.randomUUID();

    InMemoryAccountStore store = new InMemoryAccountStore();


    @Get(produces = MediaType.APPLICATION_JSON)
    public WatchList get(){
        return store.getWatchList(ACCOUNT_ID);
    }


    @Put(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
        )
    public WatchList updateWatchList(@Body WatchList watchList){
        return store.updateWatchList(ACCOUNT_ID,watchList);
    }


    @Status(HttpStatus.NO_CONTENT) // 204 code- No Content will
    @Delete(produces = MediaType.APPLICATION_JSON)
    public void delete(){
        store.deleteWatchList(ACCOUNT_ID);
    }

}
