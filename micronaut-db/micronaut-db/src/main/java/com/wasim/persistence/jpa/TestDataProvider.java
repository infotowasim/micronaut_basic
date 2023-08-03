package com.wasim.persistence.jpa;

import com.wasim.SymbolRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

@Singleton
@Requires(notEnv = Environment.TEST)
public class TestDataProvider {

   private static final Logger LOG= LoggerFactory.getLogger(TestDataProvider.class);

    private final SymbolRepository symbols;


    public TestDataProvider(SymbolRepository symbols) {
        this.symbols = symbols;
    }

    @EventListener
    public void init (StartupEvent event){
        if(symbols.findAll().isEmpty()){
            LOG.info("Adding test data as empty database ws found!!!");
            Stream.of("AAPL","AMZN","FB","TSLA")
                    .map(SymbolEntity::new)
                    .forEach(symbols::save);
        }

    }
}

