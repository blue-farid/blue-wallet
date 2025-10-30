package com.snapp.pay.insurance.bluewallet.api.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//TODO we could add custom messages
// for validation errors

@Data
public class LoginOrSignupRequest {
    @NotBlank(message = "validation.mail.blank")
    private String mail;
    @NotBlank(message = "validation.otp.blank")
    private String otp;
}
