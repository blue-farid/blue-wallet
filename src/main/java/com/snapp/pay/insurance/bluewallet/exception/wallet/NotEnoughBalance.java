package com.snapp.pay.insurance.bluewallet.exception.wallet;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class NotEnoughBalance extends BaseException {
    public static final String DESC = "exception.wallet.not-enough-balance";

    public NotEnoughBalance() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
