package com.wasim.services;

import com.wasim.model.WatchList;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.UUID;

@Singleton
public class InMemoryAccountStore {

    private final HashMap<UUID,WatchList> watchListPerAccount=new HashMap<>();

    public WatchList getWatchList(final UUID account_id) {
        return watchListPerAccount.getOrDefault(account_id, new WatchList());
    }

    public WatchList updateWatchList(final UUID account_id, WatchList watchList) {
        watchListPerAccount.put(account_id,watchList);
        return getWatchList(account_id);
    }

    public void deleteWatchList(final UUID account_id) {
        watchListPerAccount.remove(account_id);
    }
}
