package com.snapp.pay.insurance.bluewallet.exception.handler;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.model.ErrorResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.model.ValidationErrorResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.exception.BaseException;
import com.snapp.pay.insurance.bluewallet.util.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//TODO could add more exception handlers method. for now just this (RUNNING OUT OF TIME!)
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class BlueWalletExceptionHandler {
    private final MessageSourceUtil messageSource;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleBaseException(BaseException ex) {
        log.error("Business exception", ex);
        return buildResponse(ex.getStatusCode(), messageSource.getMessageIfExist(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Validation exception: {}", ex.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach((FieldError error) -> fieldErrors.put(error.getField(), messageSource.getMessageIfExist(error.getDefaultMessage())));
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(messageSource.getMessageIfExist("exception.validation-error"), fieldErrors);

        return new ResponseEntity<>(
                new ApiResponse<>(ApiStatus.FAILURE.name(), errorResponse),
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<ApiResponse<ErrorResponse>> buildResponse(HttpStatusCode status, String message) {
        return new ResponseEntity<>(new ApiResponse<>(ApiStatus.FAILURE.name(), new ErrorResponse(message)), status);
    }
}
