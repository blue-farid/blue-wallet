package com.snapp.pay.insurance.bluewallet.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;

    public ApiResponse(String status) {
        this.status = status;
    }
    // we should add more fields here for all - like messages
}
