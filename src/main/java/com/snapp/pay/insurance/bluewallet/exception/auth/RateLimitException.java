package com.snapp.pay.insurance.bluewallet.exception.auth;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class RateLimitException extends BaseException {
    public static final String DESC = "exception.auth.rate-limit";

    public RateLimitException() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.TOO_MANY_REQUESTS;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
