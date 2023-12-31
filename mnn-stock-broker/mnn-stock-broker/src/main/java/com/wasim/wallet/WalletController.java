package com.wasim.wallet;

import com.wasim.wallet.api.RestApiResponse;
import com.wasim.wallet.error.CustomError;
import com.wasim.wallet.service.InMemoryAccountStoreWallet;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import static com.wasim.wallet.service.InMemoryAccountStoreWallet.ACCOUNT_ID;

@Controller("/account/wallets")
public record WalletController(InMemoryAccountStoreWallet storeWallet) {

    public static final List<String> SUPPORTED_FIAT_CURRENCIES=List.of("EUR","USD","CHF","GBP");

    private static final Logger LOG= LoggerFactory.getLogger(WalletController.class);


    @Get(produces = MediaType.APPLICATION_JSON)
    public Collection<Wallet> get(){
        return storeWallet.getWallet(ACCOUNT_ID);
    }



    @Post(
            value = "/deposit",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public HttpResponse<RestApiResponse> depositFiatMoney(@Body DepositFiatMoney deposit){
        //Option-1 Custom HttpResponse
        if (!SUPPORTED_FIAT_CURRENCIES.contains(deposit.symbol().value())){
            return HttpResponse.badRequest()
                    .body(new CustomError(
                            HttpStatus.BAD_REQUEST.getCode(),
                            "UNSUPPORTED_FIAT_CURRENCIES",
                            String.format("Only %s are supported ", SUPPORTED_FIAT_CURRENCIES)

                    ));
        }
        var wallet = storeWallet.depositToWallet(deposit);
        LOG.debug("Deposit to wallet: {}", wallet);
        return HttpResponse.ok().body(wallet);
    }




    @Post(
            value = "/withdraw",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public void withDrawFiatMoney(@Body WithdrawFiatMoney withdraw){
        //Option-2 Custom Error Processing


    }

}
