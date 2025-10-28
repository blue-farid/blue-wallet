package com.snapp.pay.insurance.bluewallet.exception.handler;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.model.ErrorResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import com.snapp.pay.insurance.bluewallet.util.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO could add more exception handlers method. for now just this (RUNNING OUT OF TIME!)
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class BlueWalletExceptionHandler {
    private final MessageSourceUtil messageSource;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleBaseException(BaseException ex) {
        log.error("Business exception", ex);
        return buildResponse(ex.getStatusCode(), messageSource.getMessageIfExist(ex.getMessage()));
    }

    private ResponseEntity<ApiResponse<ErrorResponse>> buildResponse(HttpStatusCode status, String message) {
        return new ResponseEntity<>(new ApiResponse<>(ApiStatus.FAILURE.name(), new ErrorResponse(message)), status);
    }
}
