package com.wasim.data;

import com.github.javafaker.Faker;
import com.wasim.Symbol;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private static final Logger LOG= LoggerFactory.getLogger(InMemoryStore.class);
    private final Map<String, Symbol> symbols=new HashMap<>();
    private final Faker faker=new Faker();


    //we need to add some data
    @PostConstruct
    public void initialize(){
        initializeWith(10);
    }


    public void initializeWith(int numberOfEntries){
        symbols.clear();
        IntStream.range(0,numberOfEntries).forEach(i->
                addNewSymbol()
        );
    }

    private void addNewSymbol() {
        var symbol= new Symbol(faker.stock().nsdqSymbol());  //creating new symbol obj., nsdqSymbol()-it provides us random stock symbol
        symbols.put(symbol.value(), symbol);  // and adding hashmap by calling symbols.put()
        LOG.debug("Added Symbol {}", symbol);
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}
