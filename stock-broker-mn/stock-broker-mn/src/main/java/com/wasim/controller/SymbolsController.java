package com.wasim.controller;

import com.wasim.modelEntity.Symbol;
import com.wasim.service.InMemoryStore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("/symbols")
public class SymbolsController {

    @Inject
    private InMemoryStore inMemoryStore;

    public SymbolsController(InMemoryStore inMemoryStore) {
        this.inMemoryStore = inMemoryStore;
    }


    @Get
    public List<Symbol> getAllSymbols(){
        return new ArrayList<>(inMemoryStore.getAllSymbols().values());
    }


//    @Get("/{value}")
//    public Symbol getSymbol(@PathVariable String value){
//        return inMemoryStore.getSymbol(value);
//    }

    @Get("{value}")
    public Symbol getSymbol(@PathVariable String value){
        return inMemoryStore.getAllSymbols().get(value);
    }

    @Get("/filter{?max,offset}")
    public List<Symbol> getSymbols(@QueryValue Optional<Integer> max, @QueryValue Optional<Integer> offset){
        return inMemoryStore.getAllSymbols().values()
                .stream()
                .skip(offset.orElse(0))
//                .limit(max.orElse(0))
                .limit(max.orElse(10))
                .toList();
    }







}


