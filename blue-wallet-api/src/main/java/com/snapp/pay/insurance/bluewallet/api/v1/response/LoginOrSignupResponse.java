package com.snapp.pay.insurance.bluewallet.api.v1.response;

import com.snapp.pay.insurance.bluewallet.api.v1.model.CustomerDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginOrSignupResponse {
    private CustomerDto customer;
    private String token;
}
