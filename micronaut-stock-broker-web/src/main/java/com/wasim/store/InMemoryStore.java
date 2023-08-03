package com.wasim.store;

import com.wasim.model.Quote;
import com.wasim.model.Symbol;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.netty.util.internal.ThreadLocalRandom.current;

@Singleton
public class InMemoryStore {
    private final List<Symbol> symbols;
    private final Map<String, Quote> cachedQuote=new HashMap<>();
    private final ThreadLocalRandom current = ThreadLocalRandom.current();

    public InMemoryStore() {
        symbols = Stream.of("AAA","BBB","CCC","DDD","EEE","FFF","GGG")
                .map(Symbol::new)
                .collect(Collectors.toList());
        symbols.forEach(symbol -> {
            cachedQuote.put(symbol.getValue(),randomQuote(symbol));
        });
    }

    private Quote randomQuote(final Symbol symbol) {
        return Quote.builder().symbol(symbol)
                .bid(randomvalue(current))
                .ask(randomvalue(current))
                .lastPrice(randomvalue(current))
                .volume(randomvalue(current))
                .build();

    }

    public List<Symbol> getAllSymbols() {
        return symbols;
    }

    public Optional<Quote> fetchQuote(final String symbol) {
        return Optional.ofNullable(cachedQuote.get(symbol));
    }

    private BigDecimal randomvalue(ThreadLocalRandom current) {
        return BigDecimal.valueOf(current.nextDouble(1,100));
    }

    public void update(final Quote quote) {
        cachedQuote.put(quote.getSymbol().getValue(), quote);
    }
}
