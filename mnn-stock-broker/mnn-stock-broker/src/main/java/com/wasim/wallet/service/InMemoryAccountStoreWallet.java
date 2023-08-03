package com.wasim.wallet.service;

import com.wasim.wallet.DepositFiatMoney;
import com.wasim.wallet.Wallet;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class InMemoryAccountStoreWallet {

    public static final UUID ACCOUNT_ID=UUID.fromString("f232424-43e4336-65fh7484-678909876");

    //make sure to return proper map as type
    private final Map<UUID, Map<UUID, Wallet>> walletsPerAccount = new HashMap<>();

    public Collection<Wallet> getWallet(UUID accountId) {
        return Optional.ofNullable(walletsPerAccount.get(accountId))
                .orElse(new HashMap<>())
                .values();
    }

    public Wallet depositToWallet(DepositFiatMoney deposit) {
        final var wallets = Optional.ofNullable(
                walletsPerAccount.get(deposit.accountId())
        ).orElse()
        return null;
    }
}
