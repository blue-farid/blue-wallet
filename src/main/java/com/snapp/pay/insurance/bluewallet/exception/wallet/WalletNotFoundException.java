package com.snapp.pay.insurance.bluewallet.exception.wallet;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class WalletNotFoundException extends BaseException {
    public static final String DESC = "exception.wallet.not-found";

    public WalletNotFoundException() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
