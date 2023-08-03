package com.mithu.store;

import com.mithu.model.Symbol;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class InMemoryStore {
    private final List<Symbol> symbols;

    public InMemoryStore() {   // Initialize in constructor
        symbols = Stream.of("AAPL","AMZN","FB","GOOG","MSFT","NFLS","TSLA")
                .map(Symbol::new)
                .collect(Collectors.toList());
    }

    public List<Symbol> getAllSymbols() {
        return symbols;
    }
}
