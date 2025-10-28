package com.snapp.pay.insurance.bluewallet.exception.wallet;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class MultipleWalletException extends BaseException {
    public static final String DESC = "exception.customer-not-found";

    public MultipleWalletException() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
