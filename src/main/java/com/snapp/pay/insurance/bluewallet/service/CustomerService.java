package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;

public interface CustomerService {
    LoginOrSignupResponse login(LoginOrSignupRequest dto);
}
