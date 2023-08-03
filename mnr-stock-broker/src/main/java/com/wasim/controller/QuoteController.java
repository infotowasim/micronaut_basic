package com.wasim.controller;

import com.wasim.model.Quote;
import com.wasim.modelerror.CustomError;
import com.wasim.services.InMemoryStore;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Optional;

@Controller("/quotes")
public class QuoteController {

    private final InMemoryStore store;

    public QuoteController(InMemoryStore store) {
        this.store = store;
    }

    @Get("/{symbol}")
    public HttpResponse getQuote(String symbol){
      final Optional<Quote> mayBeQuote= store.fetchQuote(symbol);
      if (mayBeQuote.isEmpty()){
          final CustomError notFound=CustomError.builder()
                  .status(HttpStatus.NOT_FOUND.getCode())
                  .error(HttpStatus.NOT_FOUND.name())
                  .message("quote for symbol not available")
                  .path("/quotes/"+symbol)
                  .build();
          return HttpResponse.notFound(notFound);
      }
        return HttpResponse.ok(mayBeQuote.get());
    }
}
