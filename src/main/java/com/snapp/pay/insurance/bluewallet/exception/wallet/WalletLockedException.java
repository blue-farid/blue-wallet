package com.snapp.pay.insurance.bluewallet.exception.wallet;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class WalletLockedException extends BaseException {
    public static final String DESC = "exception.wallet.locked";

    public WalletLockedException() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
