package com.wasim.wallet.error;

import com.wasim.wallet.api.RestApiResponse;

public record CustomError(
        int status,
        String error,
        String message
) implements RestApiResponse {
}
