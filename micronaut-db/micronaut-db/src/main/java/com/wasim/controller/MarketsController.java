package com.wasim.controller;

import com.wasim.SymbolRepository;
import com.wasim.persistence.jpa.SymbolEntity;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Controller("/markets")
public class MarketsController {

    private final SymbolRepository symbols;

    public MarketsController(SymbolRepository symbols) {
        this.symbols = symbols;
    }


    @Operation(summary = "Return all available markets from database using JPA")
    @ApiResponse(
            content = @Content(mediaType= MediaType.APPLICATION_JSON)
    )
    @Tag(name = "markets")
    @Get("/jpa")
    public Single<List<SymbolEntity>> allSymbolsViaJpa(){
        return Single.just(symbols.findAll());
    }
}
