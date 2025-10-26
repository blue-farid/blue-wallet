package com.snapp.pay.insurance.bluewallet.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO we could add a flow for login with password too

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    //TODO handle otp with redis and mail
    @GetMapping("/otp")
    public ResponseEntity<Void> getOtp() {
        return ResponseEntity.ok().build();
    }

    //TODO login or signup
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }
}
