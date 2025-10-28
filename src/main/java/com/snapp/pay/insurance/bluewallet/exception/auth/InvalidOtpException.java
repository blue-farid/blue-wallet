package com.snapp.pay.insurance.bluewallet.exception.auth;

import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class InvalidOtpException extends BaseException {
    public static final String DESC = "exception.auth.invalid-otp";

    public InvalidOtpException() {
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
