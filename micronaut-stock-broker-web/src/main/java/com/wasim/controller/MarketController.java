package com.wasim.controller;

import com.wasim.model.Symbol;
import com.wasim.store.InMemoryStore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/markets")
public class MarketController {

    private final InMemoryStore store;

    public MarketController(InMemoryStore store) {
        this.store = store;
    }

    @Get("/")
    public List<Symbol> all(){

        return store.getAllSymbols();
    }
}
