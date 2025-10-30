package com.snapp.pay.insurance.bluewallet.api.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequest {
    @NotBlank(message = "validation.mail.blank")
    private String mail;
}
