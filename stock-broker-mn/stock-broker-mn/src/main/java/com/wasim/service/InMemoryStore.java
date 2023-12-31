package com.wasim.service;

import com.github.javafaker.Faker;
import com.wasim.modelEntity.Symbol;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final Faker faker = new Faker();

    @PostConstruct
    public void initialize(){
        initializeWith(10);

    }

    public void initializeWith(int numberOfEntries){
        symbols.clear();
        IntStream.range(0, numberOfEntries).forEach(i -> addNewSymbol());

    }

    private void addNewSymbol() {
        var symbol = new Symbol(faker.stock().nsdqSymbol());
        symbols.put(symbol.getValue(), symbol);
        LOG.debug("Added Symbol {}",symbol);

    }

    public Map<String, Symbol> getAllSymbols() {
        return symbols;
    }


//    public Symbol getSymbol(String value) {
//        return symbols.get(value);
//    }
}
