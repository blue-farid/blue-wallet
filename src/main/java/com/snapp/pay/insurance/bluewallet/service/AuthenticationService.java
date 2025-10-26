package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;

public interface AuthenticationService {
    LoginOrSignupResponse login(LoginOrSignupRequest request);
    void sendOtp(OtpRequest request);
}
