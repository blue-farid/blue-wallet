package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginOrSignupRequest request) {
        return ResponseEntity.ok().build();
    }
}
