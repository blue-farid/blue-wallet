package com.snapp.pay.insurance.bluewallet.api.v1.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    @NotBlank(message = "validation.mail.blank")
    private String mail;
}
