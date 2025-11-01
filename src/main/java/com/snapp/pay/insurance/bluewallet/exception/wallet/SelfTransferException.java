package com.snapp.pay.insurance.bluewallet.exception.wallet;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class SelfTransferException extends BaseException {
    public static final String DESC = "exception.wallet.self-transfer";

    public SelfTransferException() {
        super(DESC);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public String getDescKey() {
        return DESC;
    }
}
