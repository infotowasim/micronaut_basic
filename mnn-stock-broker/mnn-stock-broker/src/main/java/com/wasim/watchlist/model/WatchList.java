package com.wasim.watchlist.model;

import com.wasim.Symbol;

import java.util.ArrayList;
import java.util.List;

public record WatchList(List<Symbol> symbols) {

    public WatchList(){
        this(new ArrayList<>());
    }


}
